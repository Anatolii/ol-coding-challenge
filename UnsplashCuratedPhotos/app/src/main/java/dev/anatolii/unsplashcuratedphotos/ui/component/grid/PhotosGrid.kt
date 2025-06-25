package dev.anatolii.unsplashcuratedphotos.ui.component.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun PhotosGrid(
    modifier: Modifier = Modifier,
    viewModel: PhotosScreenViewModel,
    onItemSelected: (Int) -> Unit = {},
) {
    val photosPagingItems = viewModel.photos.collectAsLazyPagingItems()
    val selectedPhotoPosition by viewModel.selectedPhotoPosition.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()

    selectedPhotoPosition?.takeIf { it >= 0 }?.let { position ->
        coroutineScope.launch {
            gridState.layoutInfo.visibleItemsInfo.takeUnless { it.any { itemInfo -> itemInfo.index == position } }
                ?.let {
                    gridState.animateScrollToItem(index = position)
                }
        }
    }

    LazyVerticalGrid(
        modifier = modifier,
        state = gridState,
        columns = GridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (photosPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = stringResource(R.string.grid_message_waiting_for_items_to_load),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        // Do not use items keys as Unsplash doesn't have concept of unique ids.
        items(
            count = photosPagingItems.itemCount,
        ) { index ->
            val photo = photosPagingItems[index]
            if (photo != null) {
                PhotoGridItem(
                    photo = photo,
                    selected = selectedPhotoPosition == index,
                    modifier = Modifier.size(200.dp),
                    onItemClick = {
                        onItemSelected(index)
                        viewModel.selectedPhotoPosition.value = index
                    },
                )
            }
        }
    }
}
