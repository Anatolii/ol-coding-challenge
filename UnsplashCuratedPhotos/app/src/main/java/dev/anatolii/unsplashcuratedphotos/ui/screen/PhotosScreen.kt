package dev.anatolii.unsplashcuratedphotos.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import dev.anatolii.unsplashcuratedphotos.ui.component.DetailsViewPlaceholder
import dev.anatolii.unsplashcuratedphotos.ui.component.detail.PhotosDetailPager
import dev.anatolii.unsplashcuratedphotos.ui.component.grid.PhotosGrid
import dev.anatolii.unsplashcuratedphotos.ui.component.screen.PhotosScreenActionButton
import dev.anatolii.unsplashcuratedphotos.ui.component.screen.PhotosScreenTopBar

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(modifier: Modifier = Modifier, viewModel: PhotosScreenViewModel) {

    val backStack = rememberNavBackStack(PhotosGridNavKey)
    val listDetailStrategy = rememberListDetailSceneStrategy<Any>()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PhotosScreenTopBar(title = R.string.app_name)
        },
        floatingActionButton = {
            PhotosScreenActionButton(viewModel = viewModel)
        }
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(paddingValues),
            onBack = { keysToRemove ->
                repeat(keysToRemove) {
                    val removedEntry = backStack.removeLastOrNull()
                    if (removedEntry == PhotoDetailsNavKey) {
                        viewModel.selectedPhotoPosition.value = null
                    }
                }
            },
            sceneStrategy = listDetailStrategy,
            entryProvider = entryProvider {
                entry<PhotosGridNavKey>(
                    metadata = ListDetailSceneStrategy.listPane(
                        detailPlaceholder = {
                            DetailsViewPlaceholder(message = R.string.photo_details_view_placeholder_text)
                        }
                    )
                ) {
                    PhotosGrid(
                        viewModel = viewModel,
                        onItemSelected = {
                            backStack.add(PhotoDetailsNavKey)
                        },
                        modifier = Modifier.padding(4.dp),
                    )
                }
                entry<PhotoDetailsNavKey>(metadata = ListDetailSceneStrategy.detailPane()) {
                    PhotosDetailPager(
                        viewModel = viewModel,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        )
    }
}
