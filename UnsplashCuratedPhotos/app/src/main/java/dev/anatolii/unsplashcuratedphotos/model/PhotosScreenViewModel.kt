package dev.anatolii.unsplashcuratedphotos.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.anatolii.unsplashcuratedphotos.network.Constants
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo
import kotlinx.coroutines.flow.Flow

class PhotosScreenViewModel: ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    private val unsplashPhotosPager: Pager<Int, Photo> by lazy{ Pager<Int, Photo>(
        config = PagingConfig(
            pageSize = Constants.PhotosApi.PER_PAGE_MAX_VALUE,
            initialLoadSize = Constants.PhotosApi.PER_PAGE_MAX_VALUE,
            enablePlaceholders = true,
            maxSize = Constants.PhotosApi.PAGE_MAX_VALUE * Constants.PhotosApi.PER_PAGE_MAX_VALUE,
        ),
        initialKey = 1,
        remoteMediator = null, // to be used to implement local database caching
        pagingSourceFactory = {
            UnsplashPhotosPagingSourceFactory()
        }
    ) }

    val photos: Flow<PagingData<Photo>> by lazy {
        unsplashPhotosPager.flow.cachedIn(viewModelScope)
    }

    val selectedPhotoPosition: MutableLiveData<Int> = MutableLiveData<Int>(-1)
}
