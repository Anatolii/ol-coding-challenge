pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            val androidxSnapshotsBuildId = 13684320
            url = uri("https://androidx.dev/snapshots/builds/${androidxSnapshotsBuildId}/artifacts/repository")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            val androidxSnapshotsBuildId = 13684320
            url = uri("https://androidx.dev/snapshots/builds/${androidxSnapshotsBuildId}/artifacts/repository")
        }
    }
}

rootProject.name = "Unsplash Curated Photos"
include(":app")
