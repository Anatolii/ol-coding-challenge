interface UnsplashApiKey {
    val apiKey: Property<String>
}

val accessKey: String? = System.getProperty("UNSPLASH_API_KEY")
    ?: rootProject.layout.projectDirectory.file("local.properties").asFile.takeIf { it.exists() && it.canRead() }
        ?.inputStream()?.use { inputStream ->
            java.util.Properties().also {
                it.load(inputStream)
            }.getProperty("unsplash.access.key")
        } ?: ""

extensions.create<UnsplashApiKey>("unsplash").also { extension ->
    extension.apiKey.set(accessKey)
}