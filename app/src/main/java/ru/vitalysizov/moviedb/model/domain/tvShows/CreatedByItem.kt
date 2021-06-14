package ru.vitalysizov.moviedb.model.domain.tvShows

data class CreatedByItem(
    val id: Int,
    val creditId: String,
    val name: String,
    val gender: Int,
    val profilePath: String
)