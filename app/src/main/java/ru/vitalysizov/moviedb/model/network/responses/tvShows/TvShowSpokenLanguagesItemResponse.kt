package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName
import ru.vitalysizov.moviedb.model.network.responses.spokenLanguages.SpokenLanguagesItemResponse

data class TvShowSpokenLanguagesItemResponse(
    @SerializedName("english_name")
    val englishName: String?
) : SpokenLanguagesItemResponse()
