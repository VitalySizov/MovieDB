package ru.vitalysizov.moviedb.model.network.responses.spokenLanguages

import com.google.gson.annotations.SerializedName

open class SpokenLanguagesItemResponse(

    @SerializedName("iso_639_1")
    val iso639_1: String? = null,

    @SerializedName("name")
    val name: String? = null
)