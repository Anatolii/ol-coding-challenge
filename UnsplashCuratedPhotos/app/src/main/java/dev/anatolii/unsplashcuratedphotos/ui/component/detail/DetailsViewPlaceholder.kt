package dev.anatolii.unsplashcuratedphotos.ui.component.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.ui.theme.UnsplashCuratedPhotosTheme

/**
 * DetailsViewPlaceholder is a composable function that displays a placeholder for the details view.
 * @param modifier The modifier to be applied to the composable.
 * @param message The string resource id of the message to be displayed in the placeholder.
 */
@Composable
fun DetailsViewPlaceholder(modifier: Modifier = Modifier, @StringRes message: Int) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            text = stringResource(message)
        )
    }
}

@Composable
@Preview(name = "phone", widthDp = 320, heightDp = 200, showBackground = true)
fun DetailsViewPlaceholderPreview() {
    UnsplashCuratedPhotosTheme {
        DetailsViewPlaceholder(message = R.string.photo_details_view_placeholder_text)
    }
}

@Composable
@Preview(name = "phone Dark", widthDp = 320, heightDp = 200, showBackground = true)
fun DetailsViewPlaceholderPreviewDark() {
    UnsplashCuratedPhotosTheme(darkTheme = true) {
        DetailsViewPlaceholder(message = R.string.photo_details_view_placeholder_text)
    }
}