package dev.anatolii.unsplashcuratedphotos.ui.data

data class PhotoEntry(
    val position: Int,
    val photo: Photo,
    val isSelected: Boolean = false,
)
