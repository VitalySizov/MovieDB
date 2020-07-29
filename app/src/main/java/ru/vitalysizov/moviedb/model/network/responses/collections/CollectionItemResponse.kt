package ru.vitalysizov.moviedb.model.network.responses.collections

import com.google.gson.annotations.SerializedName

data class CollectionItemResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("poster_path")
    val posterPath: String?

)