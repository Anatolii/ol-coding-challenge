package dev.anatolii.unsplashcuratedphotos.ui.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.graphics.Color

class ColorSaver : Saver<MutableState<Color>, Double> {
    override fun SaverScope.save(value: MutableState<Color>): Double? {
        return value.value.value.toDouble()
    }

    override fun restore(value: Double): MutableState<Color>? {
        return mutableStateOf(Color(value.toULong()))
    }

}