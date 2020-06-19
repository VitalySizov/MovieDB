package ru.vitalysizov.moviedb.model.network.responses.belongsToCollection

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionItemResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
)