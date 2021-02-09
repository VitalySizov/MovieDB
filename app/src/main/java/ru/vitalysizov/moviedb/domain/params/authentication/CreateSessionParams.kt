package ru.vitalysizov.moviedb.domain.params.authentication

import com.google.gson.annotations.SerializedName

data class CreateSessionParams(
    @SerializedName("request_token")
    val requestToken: String
)