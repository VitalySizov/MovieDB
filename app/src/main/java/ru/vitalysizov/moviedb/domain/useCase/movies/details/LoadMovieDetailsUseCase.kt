package ru.vitalysizov.moviedb.domain.useCase.movies.details

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.MovieDetailsMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import javax.inject.Inject

class LoadMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val movieDetailsMapper: MovieDetailsMapper
) : SingleWithParamsUseCase<Int, MovieDetailsItem>() {

    override fun invoke(params: Int): Single<MovieDetailsItem> {
        return moviesRepository.loadMovieDetails(params).map { movieDetailsMapper.map(it) }
    }

}