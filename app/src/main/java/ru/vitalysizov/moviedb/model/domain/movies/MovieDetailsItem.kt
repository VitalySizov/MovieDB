package ru.vitalysizov.moviedb.model.domain.movies

import android.content.res.Resources
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import ru.vitalysizov.moviedb.R
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

) {
    fun getOriginalTitleAndYear(resources: Resources): String {
        val year: String = if (releaseDate.year == 0) {
            ""
        } else {
            releaseDate.year.toString()
        }

        // Only title or original title with year
        return if (year.isEmpty()) {
            resources.getString(R.string.title_mask_usual, originalTitle)
        } else {
            resources.getString(
                R.string.title_and_year_mask, originalTitle,
                releaseDate.year.toString()
            )
        }
    }

    fun getProductionCountries(): String {
        return productionCountries.joinToString { it.name }
    }

    fun getRunTime(resources: Resources): String {
        if (runTime.hour == 0 && runTime.minute == 0) return ""
        return resources.getString(
            R.string.hours_and_minutes_mask,
            runTime.hour,
            runTime.minute
        )
    }
}