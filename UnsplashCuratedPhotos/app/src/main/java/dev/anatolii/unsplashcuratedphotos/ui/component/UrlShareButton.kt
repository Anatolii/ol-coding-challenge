package dev.anatolii.unsplashcuratedphotos.ui.component

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dev.anatolii.unsplashcuratedphotos.R

/**
 * UrlShareButton is a composable function that displays a button for sharing a URL.
 * @param modifier The modifier to be applied to the composable.
 * @param url The URL to be shared.
 */
@Composable
fun UrlShareButton(modifier: Modifier, url: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        },
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_share),
            contentDescription = stringResource(R.string.share_url_button_icon_description)
        )
    }
}