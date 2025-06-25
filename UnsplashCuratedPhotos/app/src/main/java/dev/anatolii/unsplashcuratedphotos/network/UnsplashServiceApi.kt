package dev.anatolii.unsplashcuratedphotos.network

import dev.anatolii.unsplashcuratedphotos.network.data.Photo
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Unsplash service API to be used with Retrofit.
 */
internal interface UnsplashServiceApi {

    /**
     * Retrieves photos list from backend.
     * Api supports pagination starting with page 1.
     *
     * @param clientId the Unsplash API key
     * @param page page to be retrieved. Default: [Constants.PhotosApi.PAGE_MIN_VALUE]
     * @param perPage number of items to return per page. Default: [Constants.PhotosApi.PER_PAGE_MAX_VALUE]
     */
    @GET("photos")
    suspend fun photos(
        @Query(value = Constants.QueryParameterNames.CLIENT_ID) clientId: String,
        @Query(value = Constants.QueryParameterNames.PAGE) page: Int = Constants.PhotosApi.PAGE_MIN_VALUE,
        @Query(value = Constants.QueryParameterNames.PER_PAGE) perPage: Int = Constants.PhotosApi.PER_PAGE_MAX_VALUE,
    ): List<Photo>
}
