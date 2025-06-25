package dev.anatolii.unsplashcuratedphotos.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.paging.compose.collectAsLazyPagingItems
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import dev.anatolii.unsplashcuratedphotos.ui.component.DetailsViewPlaceholder
import dev.anatolii.unsplashcuratedphotos.ui.component.PhotosDetailPager
import dev.anatolii.unsplashcuratedphotos.ui.component.PhotosGrid
import dev.anatolii.unsplashcuratedphotos.ui.component.UrlShareButton

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(modifier: Modifier = Modifier, viewModel: PhotosScreenViewModel) {

    val backStack = rememberNavBackStack(PhotosGridNavKey)
    val listDetailStrategy = rememberListDetailSceneStrategy<Any>()
    val selectedPhotoPosition by viewModel.selectedPhotoPosition.observeAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(dev.anatolii.unsplashcuratedphotos.R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                modifier = Modifier,
            )
        },

        floatingActionButton = {
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
                            DetailsViewPlaceholder(message = dev.anatolii.unsplashcuratedphotos.R.string.photo_details_view_placeholder_text)
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
