package ru.vitalysizov.moviedb.domain.useCase.account

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IAccountRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.MoviesMapper
import ru.vitalysizov.moviedb.domain.params.account.RatedParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.movies.RatedMovieItem
import javax.inject.Inject

class GetRatedMoviesUseCase @Inject constructor(
    private val accountRepository: IAccountRepository,
    private val moviesMapper: MoviesMapper
) : SingleWithParamsUseCase<RatedParams, List<RatedMovieItem>>() {

    override fun invoke(params: RatedParams): Single<List<RatedMovieItem>> {
        return accountRepository.getRatedMovies(params)
            .map { it ->
                it.result?.map {
                    RatedMovieItem(
                        movieItem = moviesMapper.map(it),
                        rating = it.rating ?: 0.0
                    )
                }
            }
    }


}