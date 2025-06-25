package dev.anatolii.unsplashcuratedphotos.model

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.*
import androidx.paging.PagingState
import dev.anatolii.unsplashcuratedphotos.BuildConfig
import dev.anatolii.unsplashcuratedphotos.model.util.PhotoAdapter
import dev.anatolii.unsplashcuratedphotos.network.Constants
import dev.anatolii.unsplashcuratedphotos.network.UnsplashService
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo

/**
 * A [PagingSource] for Unsplash photos.
 * Uses [UnsplashService] to fetch the photos list from the server.
 */
class UnsplashPhotosPagingSourceFactory(
    private val unsplashService: UnsplashService = UnsplashService.withApiKey(BuildConfig.UNSPLASH_API_KEY)
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        var currentPage = params.key ?: 1

        // The logic below ensures we load all the requested photos.
        // The params.loadSize by default will equal 3xPageSize provided in [androidx.paging.PagingConfig]
        // Hence we should not make assumptions about the loadSize value
        var remainingLoadSize = params.loadSize
        val responses = mutableListOf<LoadResult<Int, Photo>>()
        while(remainingLoadSize > 0) {
            val perPage: Int = if(remainingLoadSize <= Constants.PhotosApi.PER_PAGE_MAX_VALUE) remainingLoadSize else Constants.PhotosApi.PER_PAGE_MAX_VALUE
            responses.add(load(currentPage, perPage))
            remainingLoadSize = remainingLoadSize - perPage
            currentPage = currentPage + 1
        }

        val photos = mutableListOf<Photo>()
        val errors = mutableListOf<Error<Int, Photo>>()
        responses.forEach {
            when(it) {
                is Page -> photos.addAll(it.data)
                is Error -> errors.add(it)
                is Invalid<Int, Photo> -> { /* no op */ }
            }
        }
        if(photos.isNotEmpty()) {
            return LoadResult.Page(
                data = photos,
                prevKey = null,
                nextKey = currentPage
            )
        }
        if(errors.isNotEmpty()) {
            return errors.first()
        }
        return Invalid()
    }

    /**
     * Performs single page load operation from Unsplash backend.
     */
    private suspend fun load(page: Int, perPage: Int): LoadResult<Int, Photo> {
        val protoAdapter = PhotoAdapter()
        val photos = try {
            unsplashService.photos(page, perPage).map {
                protoAdapter.adaptToUi(networkPhoto = it)
            }
        } catch (e: Exception) {
            return Error(e)
        }

        val prevKey = null
        val nextKey = if (photos.isNotEmpty()) page + 1 else null
        return LoadResult.Page<Int, Photo>(data = photos, prevKey = prevKey, nextKey = nextKey)
    }
}