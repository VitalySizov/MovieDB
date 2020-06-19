package ru.vitalysizov.moviedb.model.network.responses.movies

import com.google.gson.annotations.SerializedName

data class MovieImagesResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("backdrops")
    val backdrops: List<MovieImageItemResponse>?,

    @SerializedName("posters")
    val posters: List<MovieImageItemResponse>?
)