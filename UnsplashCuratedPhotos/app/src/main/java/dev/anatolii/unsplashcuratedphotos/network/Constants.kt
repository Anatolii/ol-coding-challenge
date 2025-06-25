package dev.anatolii.unsplashcuratedphotos.network

object Constants {
    /**
     * Base URL for Unsplash API
     */
    const val BASE_URL: String = "https://api.unsplash.com/"

    /**
     * Names of query parameters used in Unsplash API
     */
    object QueryParameterNames {
        const val CLIENT_ID = "client_id"
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
    }

    /**
     * Values for query parameters used in Unsplash `/photo` API
     */
    object PhotosApi {
        const val PAGE_MIN_VALUE = 1
        const val PAGE_MAX_VALUE = 1000
        const val PER_PAGE_MIN_VALUE = 2
        const val PER_PAGE_MAX_VALUE = 30
    }

}