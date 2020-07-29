package ru.vitalysizov.moviedb.domain.useCase.search

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.collections.CollectionMapper
import ru.vitalysizov.moviedb.domain.params.search.DefaultSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class SearchCollectionByQueryUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
    private val baseItemMapper: BaseItemMapper<CollectionItem>,
    private val collectionMapper: CollectionMapper
) : SingleWithParamsUseCase<DefaultSearchParams, BaseItem<CollectionItem>>() {

    override fun invoke(params: DefaultSearchParams): Single<BaseItem<CollectionItem>> {
        return searchRepository.searchCollections(
            query = params.query,
            page = params.page
        ).map { it ->
            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = it.result?.take(params.resultTake)?.map { collectionMapper.map(it) },
                    totalResults = it.totalResults,
                    totalPages = it.totalPages,
                    dates = it.dates
                )
            )
        }
    }
}