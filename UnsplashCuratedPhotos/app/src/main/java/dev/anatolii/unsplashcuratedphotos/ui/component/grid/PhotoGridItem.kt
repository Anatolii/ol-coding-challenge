package dev.anatolii.unsplashcuratedphotos.ui.component.grid

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

/**
 * PhotoGridItem is a composable function that displays a single photo grid item.
 * It takes a [Photo] object as input and uses the [AsyncImage] composable to display
 * the photo thumbnail.
 * @param modifier The modifier to be applied to the composable.
 * @param photo The [Photo] object containing the data for the photo to be displayed.
 * @param selected Whether the photo is selected or not.
 * @param onItemClick The callback function to be invoked when the photo is clicked.
 */
@Composable
fun PhotoGridItem(
    modifier: Modifier = Modifier,
    photo: Photo,
    selected: Boolean = false,
    onItemClick: () -> Unit = {}
) {
    val borderStroke = if (selected) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    } else {
        null
    }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .focusable(enabled = true),
        tonalElevation = 16.dp,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = borderStroke,
        onClick = onItemClick
    ) {
        AsyncImage(
            model = photo.thumbnailUrl,
            contentDescription = photo.altDescription,
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
        photo = Photo(id = "1"),
        selected = false,
        modifier = Modifier.padding(4.dp),
    )
}

@Composable
@Preview(name = "Selected", showBackground = true, widthDp = 100, heightDp = 100)
fun PhotoGridItemPreviewSelected() {
    PhotoGridItem(
        photo = Photo(id = "1"),
        selected = true,
        modifier = Modifier.padding(4.dp),
    )
}

