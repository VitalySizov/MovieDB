package ru.vitalysizov.moviedb.domain.mapper.tvShows

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.tvShows.NetworksItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.NetworksResponse
import javax.inject.Inject

class NetworksMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<NetworksResponse, NetworksItem> {
    override fun map(from: NetworksResponse): NetworksItem {
        return NetworksItem(
            name = from.name.orEmpty(),
            id = from.id ?: -1,
            logoPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.logoPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_LOGO
                )
            ),
            originCountry = from.originCountry.orEmpty()
        )
    }
}