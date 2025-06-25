package dev.anatolii.unsplashcuratedphotos.ui.component.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.paging.compose.collectAsLazyPagingItems
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import dev.anatolii.unsplashcuratedphotos.ui.component.UrlShareButton

/**
 * PhotosScreenActionButton is a composable function that displays an action button for sharing photos.
 * It takes a [PhotosScreenViewModel] as input and uses the [UrlShareButton] composable to display
 * the action button.
 * @param modifier The modifier to be applied to the composable.
 * @param viewModel The [PhotosScreenViewModel] containing the data for the photos to be displayed.
 */
@Composable
fun PhotosScreenActionButton(modifier: Modifier = Modifier, viewModel: PhotosScreenViewModel) {
    val selectedPhotoPosition by viewModel.selectedPhotoPosition.observeAsState()
    selectedPhotoPosition?.takeIf { it >= 0 }?.let { position ->
        viewModel.photos.collectAsLazyPagingItems().get(position)
            ?.shareUrl?.let { url ->
                UrlShareButton(
                    modifier = Modifier.scale(0.7f),
                    url = url,
                )
            }
    }
}
