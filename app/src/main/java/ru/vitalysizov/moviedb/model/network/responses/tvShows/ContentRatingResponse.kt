package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName

data class ContentRatingResponse(
    @SerializedName("iso_3166_1")
    val iso3166_1: String?,

    @SerializedName("rating")
    val rating: String?

)