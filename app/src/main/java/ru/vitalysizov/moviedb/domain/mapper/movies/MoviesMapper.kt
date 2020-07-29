package ru.vitalysizov.moviedb.domain.mapper.movies

import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<MovieItemResponse, MovieItem> {
    override fun map(from: MovieItemResponse): MovieItem {
        return MovieItem(
            popularity = from.popularity ?: 0.0,
            voteCount = from.voteCount ?: 0,
            video = from.video ?: false,
            posterPath = from.posterPath.orEmpty(),
            id = from.id ?: 0,
            adult = from.adult ?: false,
            backDropPath = from.backDropPath.orEmpty(),
            originalLanguage = from.originalLanguage.orEmpty(),
            originalTitle = from.originalTitle.orEmpty(),
            genreIds = from.genreIds ?: listOf(),
            releaseDate = if (from.releaseDate != null) {
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