package ru.vitalysizov.moviedb.model.domain.tvShows

import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.domain.production.ProductionCompaniesItem
import ru.vitalysizov.moviedb.model.domain.production.ProductionCountriesItem

data class TvShowDetailsItem(
    override val posterPath: String,
    override val popularity: Double,
    override val id: Int,
    override val backdropPath: String,
    override val voteAverage: String,
    override val overview: String,
    override val firstAirDate: LocalDate,
    override val originCountry: List<String>,
    override val originalLanguage: String,
    override val voteCount: Int,
    override val name: String,
    override val originalName: String,
    val createdBy: List<CreatedByItem>,
    val episodeRunTime: List<Int>,
    val genres: List<GenreItem>,
    val homePage: String,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: LocalDate,
    val lastEpisodeToAir: EpisodeToAirItem,
    val nextEpisodeToAir: EpisodeToAirItem,
    val networks: List<NetworksItem>,
    val numberOfEpisodes: Int,
    val numberOfSeason: Int,
    val productionCompanies: List<ProductionCompaniesItem>,
    val productionCountries: List<ProductionCountriesItem>,
    val seasons: List<SeasonItem>,
    val spokenLanguages: List<TvShowSpokenLanguagesItem>,
    val status: String,
    val tagline: String,
    val type: String
) : BaseTvShowItemData