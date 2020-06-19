package ru.vitalysizov.moviedb.model.network.responses.production

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItemResponse(

    @SerializedName("iso_3166_1")
    val iso3166_1: String?,

    @SerializedName("name")
    val name: String?
)