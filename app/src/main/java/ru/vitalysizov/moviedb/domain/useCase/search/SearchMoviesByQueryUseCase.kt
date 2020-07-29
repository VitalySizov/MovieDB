package ru.vitalysizov.moviedb.domain.useCase.search

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.MoviesMapper
import ru.vitalysizov.moviedb.domain.params.search.MoviesSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class SearchMoviesByQueryUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
    private val moviesMapper: MoviesMapper,
    private val baseItemMapper: BaseItemMapper<MovieItem>
) : SingleWithParamsUseCase<MoviesSearchParams, BaseItem<MovieItem>>() {

    override fun invoke(params: MoviesSearchParams): Single<BaseItem<MovieItem>> {
        return searchRepository.searchMovies(
            query = params.query,
            page = params.page,
            includeAdult = params.includeAdult,
            region = params.region,
            year = params.year,
            primaryReleaseYear = params.primaryReleaseYear
        ).map { it ->
            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = it.result?.take(params.resultTake)?.map { moviesMapper.map(it) },
                    totalResults = it.totalResults,
                    totalPages = it.totalPages,
                    dates = it.dates
                )
            )
        }
    }

}