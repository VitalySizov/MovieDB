package ru.vitalysizov.moviedb.model.network.responses.account

import com.google.gson.annotations.SerializedName

data class AvatarResponse(
    @SerializedName("gravatar")
    val graAvatarResponse: GraAvatarResponse?
)