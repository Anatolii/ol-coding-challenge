package dev.anatolii.unsplashcuratedphotos.network.data

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    val name: String?,
    val username: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("profile_image")
    val profileImage: UserProfileImage?
)
