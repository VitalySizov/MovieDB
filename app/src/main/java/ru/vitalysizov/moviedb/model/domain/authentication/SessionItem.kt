package ru.vitalysizov.moviedb.model.domain.authentication

data class SessionItem(
    val success: Boolean,
    val sessionId: String
)