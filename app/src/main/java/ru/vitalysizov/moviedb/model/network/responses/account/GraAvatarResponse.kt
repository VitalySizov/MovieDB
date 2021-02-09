package ru.vitalysizov.moviedb.model.network.responses.account

import com.google.gson.annotations.SerializedName

data class GraAvatarResponse(
    @SerializedName("hash")
    val hash: String?
)