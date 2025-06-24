package dev.anatolii.unsplashcuratedphotos.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dev.anatolii.unsplashcuratedphotos.model.PhotosScreenViewModel
import dev.anatolii.unsplashcuratedphotos.ui.screen.PhotosScreen
import dev.anatolii.unsplashcuratedphotos.ui.theme.UnsplashCuratedPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnsplashCuratedPhotosTheme {
                val viewModel: PhotosScreenViewModel by viewModels()
                PhotosScreen(viewModel = viewModel)
            }
        }
    }
}