package ru.vitalysizov.moviedb.model.domain.authentication

data class RequestTokenItem(
    val success: Boolean,
    val expiresAt: String,
    val requestToken: String
)