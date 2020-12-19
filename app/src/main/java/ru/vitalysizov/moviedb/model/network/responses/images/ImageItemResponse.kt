package ru.vitalysizov.moviedb.model.network.responses.images

import com.google.gson.annotations.SerializedName

data class ImageItemResponse(

    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,

    @SerializedName("file_path")
    val filePath: String?,

    @SerializedName("height")
    val height: Int?,

    @SerializedName("iso_639_1")
    val iso_639_1: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("width")
    val width: Int?
)