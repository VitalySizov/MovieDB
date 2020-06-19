package ru.vitalysizov.moviedb.model.domain.belongsToCollection

data class BelongsToCollectionItem(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)