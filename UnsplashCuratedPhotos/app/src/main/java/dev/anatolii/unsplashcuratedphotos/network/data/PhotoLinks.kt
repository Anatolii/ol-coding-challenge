package dev.anatolii.unsplashcuratedphotos.network.data

import com.google.gson.annotations.SerializedName

data class PhotoLinks(
    val self: String?,
    val html: String?,
    val download: String?,
    @SerializedName("download_location")
    val downloadLocation: String?
)