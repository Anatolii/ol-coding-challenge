package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo
import dev.anatolii.unsplashcuratedphotos.ui.utils.ColorSaver

@Composable
fun PhotoGridItem(modifier: Modifier = Modifier, photo: Photo, onItemClick: (Photo) -> Unit = {}) {
    var backgroundColor by rememberSaveable(saver = ColorSaver()) { mutableStateOf(Color.White) }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .focusable(enabled = true)
            .onFocusChanged {
                backgroundColor = if (it.isFocused) Color.LightGray else Color.White
            },
        tonalElevation = 16.dp,
        onClick = {
            onItemClick(photo)
        }
    ) {
        Text(
            text = photo.id,
            modifier = modifier
                .background(Color.Yellow)
                .wrapContentSize()
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 100, heightDp = 100)
fun PhotoGridItemPreview() {
    val photo = Photo(id = "1")
    PhotoGridItem(photo = photo, modifier = Modifier.padding(4.dp))
}

