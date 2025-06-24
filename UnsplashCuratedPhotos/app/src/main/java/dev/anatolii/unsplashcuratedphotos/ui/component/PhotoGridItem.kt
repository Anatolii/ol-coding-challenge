package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo

@Composable
fun PhotoGridItem(modifier: Modifier = Modifier, photo: Photo, onItemClick: (Photo) -> Unit = {}) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .focusable(enabled = true),
        tonalElevation = 16.dp,
        onClick = {
            onItemClick(photo)
        }
    ) {
        AsyncImage(
            model = photo.thumbnailUrl,
            contentDescription = photo.altDescription,
            modifier = Modifier.fillMaxSize().padding(4.dp),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background),
            fallback = painterResource(R.drawable.ic_launcher_foreground),
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 100, heightDp = 100)
fun PhotoGridItemPreview() {
    val photo = Photo(id = "1")
    PhotoGridItem(photo = photo, modifier = Modifier.padding(4.dp))
}

