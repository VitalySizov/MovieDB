package ru.vitalysizov.moviedb.domain.useCase.movies

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ITrendingRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.MoviesMapper
import ru.vitalysizov.moviedb.domain.useCase.base.BaseSingleUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import javax.inject.Inject

class LoadTrendingMoviesUseCase @Inject constructor(
    private val moviesRepository: ITrendingRepository,
    private val moviesMapper: MoviesMapper
) : BaseSingleUseCase<String, List<MovieItem>>() {

    companion object {
        const val MEDIA_TYPE_MOVIE = "movie"
    }

    override fun invoke(): Single<List<MovieItem>> = invoke()

    override fun invoke(params: String): Single<List<MovieItem>> {
        return moviesRepository.loadTrending(mediaType = MEDIA_TYPE_MOVIE, timeWindow = params)
            .map { it -> it.result?.map { moviesMapper.map(it) } }
    }
}