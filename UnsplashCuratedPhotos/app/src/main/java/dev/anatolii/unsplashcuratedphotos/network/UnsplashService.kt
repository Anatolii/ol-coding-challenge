package dev.anatolii.unsplashcuratedphotos.network

import dev.anatolii.unsplashcuratedphotos.BuildConfig
import dev.anatolii.unsplashcuratedphotos.network.data.Photo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implementation of a service that provides access to Unsplash API.
 */
class UnsplashService private constructor(
    private val apiKey: String,
    private val api: UnsplashServiceApi
) {

    /**
     * Retrieves photos list from backend.
     * Api supports pagination starting with page 1.
     *
     * @param page page to be retrieved. Default: [Constants.PhotosApi.PAGE_MIN_VALUE]
     * @param perPage number of items to return per page. Default: [Constants.PhotosApi.PER_PAGE_MAX_VALUE]
     * @throws Exception if input parameters are invalid
     */
    suspend fun photos(
        page: Int = Constants.PhotosApi.PAGE_MIN_VALUE,
        perPage: Int = Constants.PhotosApi.PER_PAGE_MAX_VALUE
    ): List<Photo> {
        val validatorsPass = listOf(
            PhotosApiPageNumberValidator(page),
            PhotosApiPerPageNumberValidator(perPage),
        ).all { it.validate() }
        if (validatorsPass) {
            return api.photos(clientId = apiKey, page = page, perPage = perPage)
        }
        throw Exception("Invalid input parameters")
    }

    companion object {

        /**
         * Creates [dev.anatolii.unsplashcuratedphotos.network.UnsplashService] instance that will use the [apiKey] for all service calls.
         *
         * @param apiKey an API key to Unsplash service
         */
        fun withApiKey(apiKey: String): UnsplashService {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                if(BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.HEADERS)
                } else {
                    setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val api = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(UnsplashServiceApi::class.java)
            return UnsplashService(apiKey, api)
        }
    }
}