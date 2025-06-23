package dev.anatolii.unsplashcuratedphotos.network

object Constants {
    const val BASE_URL: String = "https://api.unsplash.com/"

    object QueryParameterNames {
        const val CLIENT_ID = "client_id"
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
    }

    object PhotosApi {
        const val PAGE_MIN_VALUE = 1
        const val PAGE_MAX_VALUE = 1000
        const val PER_PAGE_MIN_VALUE = 2
        const val PER_PAGE_MAX_VALUE = 30
    }

}