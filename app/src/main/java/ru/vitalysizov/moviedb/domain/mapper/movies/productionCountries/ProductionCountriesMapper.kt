package ru.vitalysizov.moviedb.domain.mapper.movies.productionCountries

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.production.ProductionCountriesItem
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCountriesItemResponse
import javax.inject.Inject

class ProductionCountriesMapper @Inject constructor() :
    Mapper<List<ProductionCountriesItemResponse>?, List<ProductionCountriesItem>> {

    override fun map(from: List<ProductionCountriesItemResponse>?): List<ProductionCountriesItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                ProductionCountriesItem(
                    iso3166_1 = it.iso3166_1.orEmpty(),
                    name = it.name.orEmpty()
                )
            }
        }
    }
}