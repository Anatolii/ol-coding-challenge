package dev.anatolii.unsplashcuratedphotos.ui.data

import kotlinx.serialization.Serializable

/**
 * A data class representing a photo.
 */
@Serializable
data class Photo(
    val id: String,
    val description: String? = null,
    val altDescription: String? = null,
    val thumbnailUrl: String? = null,
    val fullSizeImageUrl: String? = null,
    val likes: Int = 0,
    val username: String? = null,
    val authorName: String? = null,
    val authorProfileImageUrl: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val shareUrl: String? = null,
)