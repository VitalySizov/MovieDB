package ru.vitalysizov.moviedb.domain.mapper.movies

import org.threeten.bp.Duration
import org.threeten.bp.LocalTime
import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.genres.GenresMapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.domain.mapper.movies.belongsToCollection.BelongsToCollectionMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.productionCompanies.ProductionCompaniesMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.productionCountries.ProductionCountriesMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.spokenLanguages.SpokenLanguagesMapper
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieDetailsItemResponse
import ru.vitalysizov.moviedb.utils.DateHelper
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor(
    private val belongsToCollectionMapper: BelongsToCollectionMapper,
    private val genresMapper: GenresMapper,
    private val productionCompaniesMapper: ProductionCompaniesMapper,
    private val productionCountriesMapper: ProductionCountriesMapper,
    private val spokenLanguagesMapper: SpokenLanguagesMapper,
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<MovieDetailsItemResponse, MovieDetailsItem> {

    override fun map(from: MovieDetailsItemResponse): MovieDetailsItem {
        return MovieDetailsItem(
            popularity = from.popularity ?: 0.0,
            voteCount = from.voteCount ?: 0,
            video = from.video ?: false,
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.posterPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_POSTER
                )
            ),
            id = from.id ?: 0,
            adult = from.adult ?: false,
            overview = from.overview.orEmpty(),
            backDropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.backDropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            ),
            originalLanguage = from.originalLanguage.orEmpty(),
            originalTitle = from.originalTitle.orEmpty(),
            releaseDate = DateHelper.getLocalDate(from.releaseDate),
            title = from.title.orEmpty(),
            voteAverage = from.voteAverage ?: 0.0,
            budget = from.budget ?: 0,
            homePage = from.homePage.orEmpty(),
            imdbId = from.imdbId.orEmpty(),
            revenue = from.revenue ?: 0,
            runTime = formatTime(from.runTime ?: 0),
            status = from.status.orEmpty(),
            tagLine = from.tagLine.orEmpty(),
            belongsToCollection = belongsToCollectionMapper.map(from.belongsToCollection),
            genres = from.genres?.map { it -> genresMapper.map(it) } ?: listOf(),
            productionCompanies = productionCompaniesMapper.map(from.productionCompanies),
            productionCountries = productionCountriesMapper.map(from.productionCountries),
            spokenLanguages = spokenLanguagesMapper.map(from.spokenLanguages)
        )
    }

    private fun formatTime(runTime: Int): LocalTime {
        val duration = Duration.ofMinutes(runTime.toLong())
        return LocalTime.ofSecondOfDay(duration.seconds)
    }
}