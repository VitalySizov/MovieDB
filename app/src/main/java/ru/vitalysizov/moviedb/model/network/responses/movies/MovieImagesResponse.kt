package ru.vitalysizov.moviedb.model.network.responses.movies

import com.google.gson.annotations.SerializedName
import ru.vitalysizov.moviedb.model.network.responses.images.ImageItemResponse

data class MovieImagesResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("backdrops")
    val backdrops: List<ImageItemResponse>?,

    @SerializedName("posters")
    val posters: List<ImageItemResponse>?
)