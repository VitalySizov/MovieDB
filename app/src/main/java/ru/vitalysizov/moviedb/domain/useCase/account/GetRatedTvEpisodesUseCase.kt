package ru.vitalysizov.moviedb.domain.useCase.account

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IAccountRepository
import ru.vitalysizov.moviedb.domain.mapper.tvEpisodes.TvEpisodesMapper
import ru.vitalysizov.moviedb.domain.params.account.RatedParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.tvEpisodes.RatedTvEpisodeItem
import javax.inject.Inject

class GetRatedTvEpisodesUseCase @Inject constructor(
    private val accountRepository: IAccountRepository,
    private val tvEpisodesMapper: TvEpisodesMapper
) : SingleWithParamsUseCase<RatedParams, List<RatedTvEpisodeItem>>() {

    override fun invoke(params: RatedParams): Single<List<RatedTvEpisodeItem>> {
        return accountRepository.getRatedTvEpisodes(params)
            .map { it -> it.result?.map { tvEpisodesMapper.map(it) } }
    }
}