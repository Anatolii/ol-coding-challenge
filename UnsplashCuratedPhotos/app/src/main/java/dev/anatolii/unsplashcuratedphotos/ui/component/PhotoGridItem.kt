package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo
import dev.anatolii.unsplashcuratedphotos.ui.data.PhotoEntry

@Composable
fun PhotoGridItem(
    modifier: Modifier = Modifier,
    photoEntry: PhotoEntry,
    onItemClick: (PhotoEntry) -> Unit = {}
) {
    val borderStroke = if (photoEntry.isSelected) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    } else {
        null
    }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .focusable(enabled = true),
        tonalElevation = 16.dp,
        color = MaterialTheme.colorScheme.secondaryContainer,
        border = borderStroke,
        onClick = {
            onItemClick(photoEntry)
        }
    ) {
        AsyncImage(
            model = photoEntry.photo.thumbnailUrl,
            contentDescription = photoEntry.photo.altDescription,
            modifier = Modifier.padding(4.dp),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background),
            fallback = painterResource(R.drawable.ic_launcher_foreground),
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 100, heightDp = 100)
fun PhotoGridItemPreview() {
    PhotoGridItem(
        photoEntry = PhotoEntry(
            position = 0,
            photo = Photo(id = "1"),
            isSelected = false,
        ),
        modifier = Modifier.padding(4.dp),
    )
}

@Composable
@Preview(name = "Selected", showBackground = true, widthDp = 100, heightDp = 100)
fun PhotoGridItemPreviewSelected() {
    PhotoGridItem(
        photoEntry = PhotoEntry(
            position = 0,
            photo = Photo(id = "1"),
            isSelected = true,
        ),
        modifier = Modifier.padding(4.dp),
    )
}

