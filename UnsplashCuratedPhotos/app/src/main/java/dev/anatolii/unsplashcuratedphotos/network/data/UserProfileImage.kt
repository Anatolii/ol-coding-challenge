package dev.anatolii.unsplashcuratedphotos.network.data

/**
 * Represents User profile image URLs as in Unsplash `/photo` API response
 */
data class UserProfileImage(
    val small: String?,
    val medium: String?,
    val large: String?,
)
