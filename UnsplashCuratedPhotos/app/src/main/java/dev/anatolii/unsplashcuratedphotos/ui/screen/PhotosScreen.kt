package dev.anatolii.unsplashcuratedphotos.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import dev.anatolii.unsplashcuratedphotos.ui.component.DetailsViewPlaceholder
import dev.anatolii.unsplashcuratedphotos.ui.component.PhotosGrid

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun PhotosScreen(modifier: Modifier = Modifier, viewModel: PhotosScreenViewModel) {

    val backStack = rememberNavBackStack(PhotosGridNavKey)
    val listDetailStrategy = rememberListDetailSceneStrategy<Any>()

    Scaffold(modifier = modifier) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(paddingValues),
            onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeLastOrNull() } },
            sceneStrategy = listDetailStrategy,
            entryProvider = entryProvider {
                entry<PhotosGridNavKey>(
                    metadata = ListDetailSceneStrategy.listPane(
                        detailPlaceholder = {
                            DetailsViewPlaceholder(message = dev.anatolii.unsplashcuratedphotos.R.string.photo_details_view_placeholder_text)
                        }
                    )
                ) {
                    PhotosGrid(modifier = Modifier, pagerFlow = viewModel.photos)
                }
            }
        )
    }
}
