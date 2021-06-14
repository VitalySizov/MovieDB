package ru.vitalysizov.moviedb.model.domain.tvShows

data class NetworksItem(
    val name: String,
    val id: Int,
    val logoPath: String,
    val originCountry: String
)