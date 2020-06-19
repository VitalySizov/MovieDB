package ru.vitalysizov.moviedb.domain.mapper.movies.productionCompanies

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.production.ProductionCompaniesItem
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCompaniesItemResponse
import javax.inject.Inject

class ProductionCompaniesMapper @Inject constructor() :
    Mapper<List<ProductionCompaniesItemResponse>?, List<ProductionCompaniesItem>> {

    override fun map(from: List<ProductionCompaniesItemResponse>?): List<ProductionCompaniesItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                ProductionCompaniesItem(
                    name = it.name.orEmpty(),
                    id = it.id ?: 0,
                    logoPath = it.logoPath.orEmpty(),
                    originalCountry = it.originalCountry.orEmpty()
                )
            }
        }
    }
}