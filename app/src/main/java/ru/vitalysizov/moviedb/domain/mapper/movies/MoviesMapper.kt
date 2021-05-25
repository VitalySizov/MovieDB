package ru.vitalysizov.moviedb.domain.mapper.movies

import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse
import javax.inject.Inject

class MoviesMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<MovieItemResponse, MovieItem> {
    override fun map(from: MovieItemResponse): MovieItem {
        return MovieItem(
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
            backDropPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.backDropPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_BACKDROP
                )
            ),
            originalLanguage = from.originalLanguage.orEmpty(),
            originalTitle = from.originalTitle.orEmpty(),
            genreIds = from.genreIds ?: listOf(),
            releaseDate = if (!from.releaseDate.isNullOrEmpty()) {
                LocalDate.parse(from.releaseDate)
            } else {
                LocalDate.of(0, 1, 1)
            },
            overview = from.overview.orEmpty(),
            title = from.title.orEmpty(),
            voteAverage = from.voteAverage.toString()
        )
    }
}