package ru.vitalysizov.moviedb.model.domain.tvShows

import ru.vitalysizov.moviedb.model.domain.enumerations.GenderType

data class CreatedByItem(
    val id: Int,
    val creditId: String,
    val name: String,
    val gender: GenderType,
    val profilePath: String
)