package ru.vitalysizov.moviedb.domain.mapper.companies

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.model.network.responses.companies.CompanyItemResponse
import javax.inject.Inject

class CompaniesMapper @Inject constructor() : Mapper<CompanyItemResponse, CompanyItem> {
    override fun map(from: CompanyItemResponse): CompanyItem {
        return CompanyItem(
            id = from.id ?: 0,
            logoPath = from.logoPath.orEmpty(),
            name = from.name.orEmpty(),
            originCountry = from.originCountry.orEmpty()
        )
    }
}