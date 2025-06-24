package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo
import kotlinx.coroutines.flow.Flow


@Composable
fun PhotosGrid(modifier: Modifier = Modifier, pagerFlow: Flow<PagingData<Photo>>) {
    val photosPagingItems = pagerFlow.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = modifier.safeContentPadding(),
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
                PhotoGridItem(photo = photo, modifier = Modifier.size(200.dp))
            }
        }
    }
}
