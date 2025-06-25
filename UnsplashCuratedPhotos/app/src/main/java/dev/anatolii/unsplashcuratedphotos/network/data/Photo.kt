package dev.anatolii.unsplashcuratedphotos.network.data

import com.google.gson.annotations.SerializedName

/**
 * Represents Photo instance as in Unsplash `/photo` API response
 */
data class Photo(
    val id: String,
    val links: PhotoLinks?,
    val likes: Int,
    val description: String?,
    @SerializedName("alt_description")
    val altDescription: String?,
    val width: Int,
    val height: Int,
    val user: User?,
    val urls: PhotoUrls,
)