package ru.vitalysizov.moviedb.domain.useCase.movies

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.MoviesMapper
import ru.vitalysizov.moviedb.domain.useCase.base.BaseSingleUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import javax.inject.Inject

class LoadTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val moviesMapper: MoviesMapper
) : BaseSingleUseCase<Unit, List<MovieItem>>() {

    override fun invoke(): Single<List<MovieItem>> {
        return moviesRepository.getTopRatedMovies().map { response ->
            response.result?.map { moviesMapper.map(it) }
        }
    }

    override fun invoke(params: Unit): Single<List<MovieItem>> = invoke()

}