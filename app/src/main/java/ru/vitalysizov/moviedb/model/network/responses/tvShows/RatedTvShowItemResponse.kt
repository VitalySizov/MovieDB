package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName

data class RatedTvShowItemResponse(
    @SerializedName("rating")
    val rating: Double
) : TvShowItemResponse()