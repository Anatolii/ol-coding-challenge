package dev.anatolii.unsplashcuratedphotos.ui.component.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

/**
 * PhotosScreenTopBar is a composable function that displays a top bar for the photos screen.
 * It takes a [Modifier] as input and uses the [TopAppBar] composable to display
 * the top bar.
 * @param modifier The modifier to be applied to the composable.
 * @param title The string resource id of the title to be displayed in the top bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreenTopBar(modifier: Modifier = Modifier, @StringRes title: Int) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(title),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        },
    )
}