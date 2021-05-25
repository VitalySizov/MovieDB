package ru.vitalysizov.moviedb.domain.mapper.movies.productionCompanies

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.production.ProductionCompaniesItem
import ru.vitalysizov.moviedb.model.network.responses.production.ProductionCompaniesItemResponse
import javax.inject.Inject

class ProductionCompaniesMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<List<ProductionCompaniesItemResponse>?, List<ProductionCompaniesItem>> {

    override fun map(from: List<ProductionCompaniesItemResponse>?): List<ProductionCompaniesItem> {
        return if (from.isNullOrEmpty()) {
            emptyList()
        } else {
            from.map {
                ProductionCompaniesItem(
                    name = it.name.orEmpty(),
                    id = it.id ?: 0,
                    logoPath = imageUrlMapper.map(
                        UrlPathAndType(
                            path = it.logoPath.orEmpty(),
                            imageType = ImageTypes.IMAGE_LOGO
                        )
                    ),
                    originalCountry = it.originalCountry.orEmpty()
                )
            }
        }
    }
}