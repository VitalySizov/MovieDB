package ru.vitalysizov.moviedb.model.network.responses.keywords

import com.google.gson.annotations.SerializedName

data class KeywordItemResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?
)