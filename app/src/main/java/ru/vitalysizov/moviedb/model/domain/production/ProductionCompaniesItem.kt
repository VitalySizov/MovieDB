package ru.vitalysizov.moviedb.model.domain.production

data class ProductionCompaniesItem(
    val name: String,
    val id: Int,
    val logoPath: String,
    val originalCountry: String
)