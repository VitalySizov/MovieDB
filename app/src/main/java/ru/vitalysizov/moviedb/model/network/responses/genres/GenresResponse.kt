package ru.vitalysizov.moviedb.model.network.responses.genres

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<GenreItemResponse>?
)