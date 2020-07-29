package ru.vitalysizov.moviedb.domain.useCase.search

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.tvShows.TvShowsMapper
import ru.vitalysizov.moviedb.domain.params.search.TvShowsSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class SearchTvShowsByQueryUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
    private val tvShowsMapper: TvShowsMapper,
    private val baseItemMapper: BaseItemMapper<TvShowItem>
) : SingleWithParamsUseCase<TvShowsSearchParams, BaseItem<TvShowItem>>() {

    override fun invoke(params: TvShowsSearchParams): Single<BaseItem<TvShowItem>> {
        return searchRepository.searchTvShows(
            query = params.query,
            page = params.page,
            includeAdult = params.includeAdult,
            firstAirDateYear = params.firstAirDateYear
        ).map { it ->
            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = it.result?.take(params.resultTake)?.map { tvShowsMapper.map(it) },
                    totalPages = it.totalPages,
                    totalResults = it.totalResults,
                    dates = it.dates
                )
            )
        }
    }
}