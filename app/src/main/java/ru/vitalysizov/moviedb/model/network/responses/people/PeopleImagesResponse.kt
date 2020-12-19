package ru.vitalysizov.moviedb.model.network.responses.people

import com.google.gson.annotations.SerializedName
import ru.vitalysizov.moviedb.model.network.responses.images.ImageItemResponse

data class PeopleImagesResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("profiles")
    val imagesList: List<ImageItemResponse>
)