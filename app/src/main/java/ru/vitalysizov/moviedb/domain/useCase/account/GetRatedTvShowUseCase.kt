package ru.vitalysizov.moviedb.domain.useCase.account

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IAccountRepository
import ru.vitalysizov.moviedb.domain.mapper.tvShows.TvShowsMapper
import ru.vitalysizov.moviedb.domain.params.account.RatedParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.tvShows.RatedTvShowItem
import javax.inject.Inject

class GetRatedTvShowUseCase @Inject constructor(
    private val accountRepository: IAccountRepository,
    private val tvShowsMapper: TvShowsMapper
) : SingleWithParamsUseCase<RatedParams, List<RatedTvShowItem>>() {

    override fun invoke(params: RatedParams): Single<List<RatedTvShowItem>> {
        return accountRepository.getRatedTvShow(params)
            .map { it ->
                it.result?.map {
                    RatedTvShowItem(
                        tvShowItem = tvShowsMapper.map(it),
                        rating = it.rating
                    )
                }
            }
    }
}
