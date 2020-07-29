package ru.vitalysizov.moviedb.domain.useCase.search

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.companies.CompaniesMapper
import ru.vitalysizov.moviedb.domain.params.search.DefaultSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import javax.inject.Inject

class SearchCompaniesByQueryUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
    private val baseItemMapper: BaseItemMapper<CompanyItem>,
    private val companiesMapper: CompaniesMapper
) : SingleWithParamsUseCase<DefaultSearchParams, BaseItem<CompanyItem>>() {

    override fun invoke(params: DefaultSearchParams): Single<BaseItem<CompanyItem>> {
        return searchRepository.searchCompanies(
            query = params.query,
            page = params.page
        ).map { it ->
            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = it.result?.take(params.resultTake)?.map { companiesMapper.map(it) },
                    totalResults = it.totalResults,
                    totalPages = it.totalPages,
                    dates = it.dates
                )
            )
        }
    }
}