package ru.vitalysizov.moviedb.domain.useCase.tvShows

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.domain.mapper.tvShows.TvShowsMapper
import ru.vitalysizov.moviedb.domain.useCase.base.BaseSingleUseCase
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import javax.inject.Inject

class LoadPopularTvShowsUseCase @Inject constructor(
    private val tvShowRepository: ITvShowsRepository,
    private val tvShowsMapper: TvShowsMapper
) : BaseSingleUseCase<Unit, List<TvShowItem>>() {

    override fun invoke(): Single<List<TvShowItem>> {
        return tvShowRepository.getTvShowPopular().map { response ->
            response.result?.map { tvShowsMapper.map(it) }
        }
    }

    override fun invoke(params: Unit): Single<List<TvShowItem>> = invoke()
}