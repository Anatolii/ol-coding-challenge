package dev.anatolii.unsplashcuratedphotos.model.util

import dev.anatolii.unsplashcuratedphotos.network.data.Photo

class PhotoAdapter {
    fun adaptToUi(networkPhoto: Photo): dev.anatolii.unsplashcuratedphotos.ui.data.Photo {
        return dev.anatolii.unsplashcuratedphotos.ui.data.Photo(
            id = networkPhoto.id,
            description = networkPhoto.description,
            altDescription = networkPhoto.altDescription,
            thumbnailUrl = networkPhoto.urls.small,
            fullSizeImageUrl = networkPhoto.urls.full,
            likes = networkPhoto.likes,
            username = networkPhoto.user?.username,
            authorName = networkPhoto.user?.name,
            authorProfileImageUrl = networkPhoto.user?.profileImage?.medium,
            width = networkPhoto.width,
            height = networkPhoto.height,
            shareUrl = networkPhoto.links?.html,
        )
    }
}