package dev.anatolii.unsplashcuratedphotos.network.data

import com.google.gson.annotations.SerializedName

/**
 * Represents Photo URLs that are part of `/photo` API response
 */
data class PhotoUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    @SerializedName("small_s3")
    val smallS3: String
)
