package ru.vitalysizov.moviedb.model.domain.persons

data class PersonItem(
    val popularity: Double,
    val knownForDepartment: String,
    val name: String,
    val id: Int,
    val profilePath: String,
    val adult: Boolean,
    val gender: Int

    //TODO add know_for
)