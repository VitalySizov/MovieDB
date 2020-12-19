package ru.vitalysizov.moviedb.model.domain.person

data class PersonCombinedCredits(
    val cast: List<Any>,
    val crew: List<PersonCrewItem>,
    val id: Int
)