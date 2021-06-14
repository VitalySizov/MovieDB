package ru.vitalysizov.moviedb.model.network.responses.tvShows

import com.google.gson.annotations.SerializedName
import ru.vitalysizov.moviedb.model.network.responses.genres.GenreItemResponse
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCompaniesItemResponse
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCountriesItemResponse

data class TvShowDetailsItemResponse(

    @SerializedName("created_by")
    val createdByItem: List<CreatedByItemResponse>?,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>?,

    @SerializedName("genres")
    val genres: List<GenreItemResponse>?,

    @SerializedName("homepage")
    val homePage: String?,

    @SerializedName("in_production")
    val inProduction: Boolean?,

    @SerializedName("languages")
    val languages: List<String>?,

    @SerializedName("last_air_date")
    val lastAirDate: String?,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeToAirResponse?,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeToAirResponse?,

    @SerializedName("networks")
    val networks: List<NetworksResponse>?,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int?,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItemResponse>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItemResponse>?,

    @SerializedName("seasons")
    val seasons: List<SeasonItemResponse>?,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<TvShowSpokenLanguagesItemResponse>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("type")
    val type: String?

) : TvShowItemResponse()
