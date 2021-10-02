package ru.vitalysizov.moviedb.domain.mapper.tvShows

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.genres.GenresMapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.domain.mapper.movies.productionCompanies.ProductionCompaniesMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.productionCountries.ProductionCountriesMapper
import ru.vitalysizov.moviedb.model.domain.enumerations.TvShowStatus
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowDetailsItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowDetailsItemResponse
import ru.vitalysizov.moviedb.utils.DateHelper
import javax.inject.Inject

class TvShowDetailsMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper,
    private val genresMapper: GenresMapper,
    private val productionCompaniesMapper: ProductionCompaniesMapper,
    private val productionCountriesMapper: ProductionCountriesMapper,
    private val episodeToAirMapper: EpisodeToAirMapper,
    private val createdByMapper: CreatedByMapper,
    private val networksMapper: NetworksMapper,
    private val seasonsMapper: SeasonsMapper,
    private val tvShowSpokenLanguagesMapper: TvShowSpokenLanguagesMapper
) : Mapper<TvShowDetailsItemResponse, TvShowDetailsItem> {

    override fun map(from: TvShowDetailsItemResponse): TvShowDetailsItem {
        return TvShowDetailsItem(
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.posterPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_POSTER
                )
            ),
            popularity = from.popularity ?: 0.0,
            id = from.id ?: -1,
            backdropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.backdropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            ),
            voteAverage = from.voteAverage?.toString() ?: "0.0",
            overview = from.overview.orEmpty(),
            firstAirDate = DateHelper.getLocalDate(from.firstAirDate),
            originCountry = from.originCountry?.map { it } ?: listOf(),
            originalLanguage = from.originalLanguage.orEmpty(),
            voteCount = from.voteCount ?: 0,
            name = from.name.orEmpty(),
            originalName = from.originalName.orEmpty(),
            createdBy = from.createdByItem?.map { createdByMapper.map(it) } ?: emptyList(),
            episodeRunTime = from.episodeRunTime ?: emptyList(),
            genres = from.genres?.map { genresMapper.map(it) } ?: emptyList(),
            homePage = from.homePage.orEmpty(),
            inProduction = from.inProduction ?: false,
            languages = from.languages ?: emptyList(),
            lastAirDate = DateHelper.getLocalDate(from.lastAirDate),
            lastEpisodeToAir = episodeToAirMapper.map(from.lastEpisodeToAir),
            nextEpisodeToAir = episodeToAirMapper.map(from.nextEpisodeToAir),
            networks = from.networks?.map { networksMapper.map(it) } ?: emptyList(),
            numberOfEpisodes = from.numberOfEpisodes ?: 0,
            numberOfSeason = from.numberOfSeasons ?: 0,
            productionCompanies = productionCompaniesMapper.map(from.productionCompanies),
            productionCountries = productionCountriesMapper.map(from.productionCountries),
            seasons = from.seasons?.map { seasonsMapper.map(it) } ?: emptyList(),
            spokenLanguages = from.spokenLanguages?.map { tvShowSpokenLanguagesMapper.map(it) } ?: emptyList(),
            status = TvShowStatus.fromIdentifier(from.status.orEmpty()),
            tagline = from.tagline.orEmpty(),
            type = from.type.orEmpty()
        )
    }
}