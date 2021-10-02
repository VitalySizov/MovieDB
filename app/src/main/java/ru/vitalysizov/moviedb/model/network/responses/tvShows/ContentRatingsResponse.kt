package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName

data class ContentRatingsResponse(
    @SerializedName("results")
    val results: List<ContentRatingResponse>?,

    @SerializedName("id")
    val id: Int?
)