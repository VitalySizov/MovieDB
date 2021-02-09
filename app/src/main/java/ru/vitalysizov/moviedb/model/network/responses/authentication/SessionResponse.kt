package ru.vitalysizov.moviedb.model.network.responses.authentication

import com.google.gson.annotations.SerializedName

data class SessionResponse(
    @SerializedName("success")
    val success: Boolean?,

    @SerializedName("session_id")
    val sessionId: String?
)