package ru.vitalysizov.moviedb.model.domain.companies

data class CompanyItem(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)