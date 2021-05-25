package ru.vitalysizov.moviedb.domain.params.authentication

import com.google.gson.annotations.SerializedName

data class DeleteSessionParams(
    @SerializedName("session_id")
    val sessionId: String
)