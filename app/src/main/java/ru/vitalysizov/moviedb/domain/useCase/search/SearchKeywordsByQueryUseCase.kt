package ru.vitalysizov.moviedb.domain.useCase.search

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.keywords.KeywordMapper
import ru.vitalysizov.moviedb.domain.params.search.DefaultSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.keywords.KeywordItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class SearchKeywordsByQueryUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
    private val baseItemMapper: BaseItemMapper<KeywordItem>,
    private val keywordMapper: KeywordMapper
) : SingleWithParamsUseCase<DefaultSearchParams, BaseItem<KeywordItem>>() {

    override fun invoke(params: DefaultSearchParams): Single<BaseItem<KeywordItem>> {
        return searchRepository.searchKeywords(
            query = params.query,
            page = params.page
        ).map { it ->
            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = it.result?.take(params.resultTake)?.map { keywordMapper.map(it) },
                    totalPages = it.totalPages,
                    dates = it.dates,
                    totalResults = it.totalResults
                )
            )
        }
    }
}