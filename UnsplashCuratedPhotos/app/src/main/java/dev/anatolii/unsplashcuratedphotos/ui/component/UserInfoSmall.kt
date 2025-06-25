package dev.anatolii.unsplashcuratedphotos.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.anatolii.unsplashcuratedphotos.R

/**
 * UserInfoSmall is a composable function that displays the user information.
 * @param modifier The modifier to be applied to the composable.
 * @param name The name of the user.
 * @param profileImageUrl The URL of the user's profile image.
 */
@Composable
fun UserInfoSmall(modifier: Modifier, name: String, profileImageUrl: String?) {
    Row(
        modifier = modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = stringResource(R.string.photo_author_profile_avatar_image_description),
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background),
            fallback = painterResource(R.drawable.ic_launcher_foreground),
        )
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 200, heightDp = 200)
fun UserInfoSmallPreview() {
    UserInfoSmall(
        modifier = Modifier,
        name = "John Doe",
        profileImageUrl = "https://example.com/image.jpg"
    )
}