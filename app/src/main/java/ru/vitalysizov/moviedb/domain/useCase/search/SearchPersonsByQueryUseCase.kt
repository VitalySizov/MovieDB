package ru.vitalysizov.moviedb.domain.useCase.search

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.persons.PersonsMapper
import ru.vitalysizov.moviedb.domain.params.search.PersonsSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class SearchPersonsByQueryUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
    private val personMapper: PersonsMapper,
    private val baseItemMapper: BaseItemMapper<PersonItem>
) : SingleWithParamsUseCase<PersonsSearchParams, BaseItem<PersonItem>>() {

    override fun invoke(params: PersonsSearchParams): Single<BaseItem<PersonItem>> {
        return searchRepository.searchPersons(
            query = params.query,
            page = params.page,
            includeAdult = params.includeAdult,
            region = params.region
        ).map { it ->
            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = it.result?.take(params.resultTake)?.map { personMapper.map(it) },
                    totalPages = it.totalPages,
                    totalResults = it.totalResults,
                    dates = it.dates
                )
            )
        }
    }
}