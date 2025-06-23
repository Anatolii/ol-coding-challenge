package dev.anatolii.unsplashcuratedphotos

import org.junit.Test

class BuildConfigTest {

    @Test
    fun `unsplash api key should be set`() {
        val apiKey = BuildConfig.UNSPLASH_API_KEY
        assert(apiKey.isNotBlank())
        assert(apiKey.isNotEmpty())
        assert(apiKey.length == 43)
    }
}