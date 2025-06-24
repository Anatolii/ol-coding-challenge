package dev.anatolii.unsplashcuratedphotos.ui.screen

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * The navigation key to be used to show a grid of photos
 */
@Serializable
object PhotosGridNavKey: NavKey

/**
 * The navigation key to be used to show a photo with details
 */
@Serializable
object PhotoDetailsNavKey: NavKey