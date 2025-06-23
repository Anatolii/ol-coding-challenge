package dev.anatolii.unsplashcuratedphotos.network

import android.util.Log
import dev.anatolii.unsplashcuratedphotos.network.Constants.PhotosApi

/**
 * Validates a condition
 */
sealed interface Validator {
    /**
     * Validates the condition
     * @return true when condition passes validation or false otherwise.
     */
    fun validate(): Boolean
}

data class PhotosApiPageNumberValidator(val page: Int) : Validator {
    override fun validate(): Boolean {
        if (page < PhotosApi.PAGE_MIN_VALUE) {
            Log.d(this::class.simpleName, "Page can not be less than ${PhotosApi.PAGE_MIN_VALUE}")
            return false
        } else if (page > PhotosApi.PAGE_MAX_VALUE) {
            Log.d(
                this::class.simpleName,
                "Page can not be greater than ${PhotosApi.PAGE_MAX_VALUE}"
            )
            return false
        }
        return true
    }
}

data class PhotosApiPerPageNumberValidator(val perPage: Int) : Validator {
    override fun validate(): Boolean {
        if (perPage < PhotosApi.PER_PAGE_MIN_VALUE) {
            Log.d(
                this::class.simpleName,
                "There should be at lease ${PhotosApi.PER_PAGE_MIN_VALUE} per page"
            )
            return false
        } else if (perPage > PhotosApi.PER_PAGE_MAX_VALUE) {
            Log.d(
                this::class.simpleName,
                "There should be not more than ${PhotosApi.PER_PAGE_MAX_VALUE} per page"
            )
            return false
        }
        return true
    }
}