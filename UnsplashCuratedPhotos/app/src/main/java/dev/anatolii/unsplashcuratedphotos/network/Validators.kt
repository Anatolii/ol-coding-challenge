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

/**
 * Validates that the page number is within the allowed range: [[PhotosApi.PAGE_MIN_VALUE] : [PhotosApi.PAGE_MAX_VALUE]]
 * @param page the page number to be validated
 * @return true when the page number is valid or false otherwise
 */
data class PhotosApiPageNumberValidator(val page: Int) : Validator {
    override fun validate(): Boolean {
        return if (page < PhotosApi.PAGE_MIN_VALUE) {
            Log.d(this::class.simpleName, "Page can not be less than ${PhotosApi.PAGE_MIN_VALUE}")
            false
        } else if (page > PhotosApi.PAGE_MAX_VALUE) {
            Log.d(
                this::class.simpleName,
                "Page can not be greater than ${PhotosApi.PAGE_MAX_VALUE}"
            )
            false
        } else {
            true
        }
    }
}

/**
 * Validates that the per page number is within the allowed range: [[PhotosApi.PER_PAGE_MIN_VALUE] : [PhotosApi.PER_PAGE_MAX_VALUE]]
 */
data class PhotosApiPerPageNumberValidator(val perPage: Int) : Validator {
    override fun validate(): Boolean {
        return if (perPage < PhotosApi.PER_PAGE_MIN_VALUE) {
            Log.d(
                this::class.simpleName,
                "There should be at lease ${PhotosApi.PER_PAGE_MIN_VALUE} per page"
            )
            false
        } else if (perPage > PhotosApi.PER_PAGE_MAX_VALUE) {
            Log.d(
                this::class.simpleName,
                "There should be not more than ${PhotosApi.PER_PAGE_MAX_VALUE} per page"
            )
            false
        } else {
            true
        }
    }
}