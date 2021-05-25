package ru.vitalysizov.moviedb.model.network.responses.movies

import com.google.gson.annotations.SerializedName

class RatedMovieItemResponse(
    @SerializedName("rating")
    val rating: Double? = null
) : MovieItemResponse()