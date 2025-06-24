package dev.anatolii.unsplashcuratedphotos.ui.component

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
import dev.anatolii.unsplashcuratedphotos.ui.theme.UnsplashCuratedPhotosTheme

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
        DetailsViewPlaceholder(message = dev.anatolii.unsplashcuratedphotos.R.string.photo_details_view_placeholder_text)
    }
}

@Composable
@Preview(name = "phone Dark", widthDp = 320, heightDp = 200, showBackground = true)
fun DetailsViewPlaceholderPreviewDark() {
    UnsplashCuratedPhotosTheme(darkTheme = true) {
        DetailsViewPlaceholder(message = dev.anatolii.unsplashcuratedphotos.R.string.photo_details_view_placeholder_text)
    }
}