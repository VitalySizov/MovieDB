package ru.vitalysizov.moviedb.domain.mapper.movies

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse
import javax.inject.Inject

class MoviesMapper @Inject constructor() : Mapper<MovieResponse, MovieItem> {
    override fun map(from: MovieResponse): MovieItem {
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
            releaseDate = from.releaseDate.orEmpty(),
            overview = from.overview.orEmpty(),
            title = from.title.orEmpty(),
            voteAverage = from.voteAverage ?: 0.0
        )
    }
}