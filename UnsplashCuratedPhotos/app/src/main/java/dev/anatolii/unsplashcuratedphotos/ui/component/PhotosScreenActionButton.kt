package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.paging.compose.collectAsLazyPagingItems
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel

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
