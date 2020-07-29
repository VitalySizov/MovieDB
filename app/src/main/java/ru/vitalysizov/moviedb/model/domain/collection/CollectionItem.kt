package ru.vitalysizov.moviedb.model.domain.collection

data class CollectionItem(
    val id: Int,
    val backdropPath: String,
    val name: String,
    val posterPath: String
)