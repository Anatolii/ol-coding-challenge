package dev.anatolii.unsplashcuratedphotos.ui.component.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun PhotosDetailPager(modifier: Modifier = Modifier, viewModel: PhotosScreenViewModel) {
    val photosPagingItems = viewModel.photos.collectAsLazyPagingItems()
    val selectedPhotoPosition by viewModel.selectedPhotoPosition.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { photosPagingItems.itemCount })

    selectedPhotoPosition?.takeIf { it >= 0}?.let { position ->
        coroutineScope.launch {
            pagerState.layoutInfo.visiblePagesInfo.takeUnless { it.any { pageInfo -> pageInfo.index == position } }
                ?.let {
                    pagerState.animateScrollToPage(page =position)
                }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.selectedPhotoPosition.value = page
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        pageSpacing = 8.dp
    ) { page ->
        photosPagingItems[page]?.let {
            PhotoDetailsItem(
                modifier = Modifier.fillMaxSize(),
                photo = it
            )
        }
    }
}