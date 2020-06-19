package ru.vitalysizov.moviedb.model.network.responses.movies

import com.google.gson.annotations.SerializedName
import ru.vitalysizov.moviedb.model.network.responses.belongsToCollection.BelongsToCollectionItemResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenreItemResponse
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCompaniesItemResponse
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCountriesItemResponse
import ru.vitalysizov.moviedb.model.network.responses.spokenLanguages.SpokenLanguagesItemResponse

data class MovieDetailsItemResponse(

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionItemResponse?,

    @SerializedName("budget")
    val budget: Int?,

    @SerializedName("genres")
    val genres: List<GenreItemResponse>?,

    @SerializedName("homepage")
    val homePage: String?,

    @SerializedName("imdb_id")
    val imdbId: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItemResponse>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItemResponse>?,

    @SerializedName("revenue")
    val revenue: Int?,

    @SerializedName("runtime")
    val runTime: Int?,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItemResponse>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagLine: String?

)



