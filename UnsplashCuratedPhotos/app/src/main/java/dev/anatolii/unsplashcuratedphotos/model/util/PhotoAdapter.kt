package dev.anatolii.unsplashcuratedphotos.model.util

import dev.anatolii.unsplashcuratedphotos.network.data.Photo

class PhotoAdapter {
    fun adaptToUi(networkPhoto: Photo): dev.anatolii.unsplashcuratedphotos.ui.data.Photo {
        return dev.anatolii.unsplashcuratedphotos.ui.data.Photo(
            id = networkPhoto.id,
        )
    }
}