package ru.vitalysizov.moviedb.domain.mapper.companies

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.model.network.responses.companies.CompanyItemResponse
import javax.inject.Inject

class CompaniesMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<CompanyItemResponse, CompanyItem> {
    override fun map(from: CompanyItemResponse): CompanyItem {
        return CompanyItem(
            id = from.id ?: 0,
            logoPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.logoPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_LOGO
                )
            ),
            name = from.name.orEmpty(),
            originCountry = from.originCountry.orEmpty()
        )
    }
}