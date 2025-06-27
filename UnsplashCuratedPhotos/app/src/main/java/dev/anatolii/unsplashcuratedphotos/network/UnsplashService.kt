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
    ): Result<List<Photo>> {
        val validatorsPass = listOf(
            PhotosApiPageNumberValidator(page),
            PhotosApiPerPageNumberValidator(perPage),
        ).all { it.validate() }
        if (validatorsPass) {
            return getCuratedPhotos(apiKey, page, perPage)
        }
        throw Exception("Invalid input parameters")
    }

    suspend fun getCuratedPhotos(clientId: String, page: Int, perPage: Int): Result<List<Photo>> {
        return try {
            val response = api.photos(clientId, page, perPage)

            if (response.isSuccessful) {
                response.body()?.let { photos ->
                    Result.success(photos)
                } ?: Result.failure(Exception("Response body is null"))
            } else {
                val errorBody = response.errorBody()?.string() // Get error message from server
                val errorCode = response.code()
                Result.failure(Exception("API Error: $errorCode - ${errorBody ?: "Unknown error"}"))
            }
        } catch (e: Exception) {
            // Handle other exceptions like network issues, etc.
            Result.failure(e)
        }
    }

    companion object {

        /**
         * Creates [dev.anatolii.unsplashcuratedphotos.network.UnsplashService] instance that will use the [apiKey] for all service calls.
         *
         * @param apiKey an API key to Unsplash service
         */
        fun withApiKey(apiKey: String): UnsplashService {
            val api = createServiceApi()
            return UnsplashService(apiKey, api)
        }

        private fun createServiceApi(
            client: OkHttpClient = createHttpClient()
        ): UnsplashServiceApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UnsplashServiceApi::class.java)

        private fun createHttpClient(
            loggingInterceptor: HttpLoggingInterceptor = createLoggingInterceptor()
        ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        private fun createLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.HEADERS)
                } else {
                    setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            }
    }
}