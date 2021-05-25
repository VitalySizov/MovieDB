package ru.vitalysizov.moviedb.model.network.responses.authentication

import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @SerializedName("success")
    val success: Boolean?
)