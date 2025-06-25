package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.anatolii.unsplashcuratedphotos.R
import dev.anatolii.unsplashcuratedphotos.ui.data.Photo

@Composable
fun PhotoDetailsItem(modifier: Modifier, photo: Photo) {
    val childModifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .background(color = MaterialTheme.colorScheme.tertiaryContainer)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .horizontalScroll(state = rememberScrollState())
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                photo.authorName?.let {
                    UserInfoSmall(
                        modifier = childModifier,
                        name = it,
                        profileImageUrl = photo.authorProfileImageUrl
                    )
                }
                VerticalDivider(thickness = 1.dp)
                LikesInfoSmall(modifier = childModifier, likes = photo.likes)
            }
            (photo.description ?: photo.altDescription)?.let {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    thickness = 1.dp,
                )
                Text(
                    modifier = childModifier
                        .align(Alignment.Start)
                        .padding(4.dp)
                        .fillMaxWidth(),
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
            )
            AsyncImage(
                model = photo.fullSizeImageUrl,
                contentDescription = photo.altDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_background),
                fallback = painterResource(R.drawable.ic_launcher_foreground),
            )
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 200)
fun PhotoDetailsItemPreview() {
    val photo = Photo(
        id = "1",
        description = "A beautiful landscape",
        altDescription = "A stunning view of mountains and a lake",
        thumbnailUrl = "https://example.com/thumbnail.jpg",
        fullSizeImageUrl = "https://example.com/fullsize.jpg",
        likes = 100,
        username = "johndoe",
        authorName = "John Doe",
        authorProfileImageUrl = "https://example.com/profile.jpg",
        width = 1920,
        height = 1080,
        shareUrl = "https://example.com/share"
    )
    PhotoDetailsItem(modifier = Modifier, photo = photo)
}
