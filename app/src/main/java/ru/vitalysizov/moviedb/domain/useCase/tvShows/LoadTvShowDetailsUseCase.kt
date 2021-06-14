package ru.vitalysizov.moviedb.domain.useCase.tvShows

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.domain.mapper.tvShows.*
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.tvShows.*
import javax.inject.Inject

class LoadTvShowDetailsUseCase @Inject constructor(
    private val tvShowRepository: ITvShowsRepository,
    private val tvShowDetailsMapper: TvShowDetailsMapper
) : SingleWithParamsUseCase<Int, TvShowDetailsItem>() {

    override fun invoke(params: Int): Single<TvShowDetailsItem> {
        return tvShowRepository.getTvShowById(params).map { tvShowDetailsMapper.map(it) }
    }
}