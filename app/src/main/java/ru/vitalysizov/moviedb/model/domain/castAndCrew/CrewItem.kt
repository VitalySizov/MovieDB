package ru.vitalysizov.moviedb.model.domain.castAndCrew

data class CrewItem(
    val creditId: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val name: String,
    val profilePath: String
)