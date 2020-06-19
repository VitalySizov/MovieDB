package ru.vitalysizov.moviedb.model.domain.movies

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.domain.belongsToCollection.BelongsToCollectionItem
import ru.vitalysizov.moviedb.model.domain.production.ProductionCompaniesItem
import ru.vitalysizov.moviedb.model.domain.production.ProductionCountriesItem
import ru.vitalysizov.moviedb.model.domain.spokenLanguages.SpokenLanguagesItem

data class MovieDetailsItem(
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val posterPath: String,
    val id: Int,
    val adult: Boolean,
    val overview: String,
    val backDropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val releaseDate: LocalDate,
    val title: String,
    val voteAverage: Double,
    val budget: Int,
    val homePage: String,
    val imdbId: String,
    val revenue: Int,
    val runTime: LocalTime,
    val status: String,
    val tagLine: String,
    val belongsToCollection: BelongsToCollectionItem,
    val genres: List<GenreItem>,
    val productionCompanies: List<ProductionCompaniesItem>,
    val productionCountries: List<ProductionCountriesItem>,
    val spokenLanguages: List<SpokenLanguagesItem>

)