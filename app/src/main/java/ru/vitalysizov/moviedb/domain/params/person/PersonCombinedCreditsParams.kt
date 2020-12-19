package ru.vitalysizov.moviedb.domain.params.person

data class PersonCombinedCreditsParams(
    val personId: Int,
    val sortedByDescendingReleaseDate: Boolean
)