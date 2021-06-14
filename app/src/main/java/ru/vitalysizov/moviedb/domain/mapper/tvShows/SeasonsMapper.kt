package ru.vitalysizov.moviedb.domain.mapper.tvShows

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.tvShows.SeasonItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.SeasonItemResponse
import ru.vitalysizov.moviedb.utils.DateHelper
import javax.inject.Inject

class SeasonsMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<SeasonItemResponse, SeasonItem> {
    override fun map(from: SeasonItemResponse): SeasonItem {
        return SeasonItem(
            airDate = DateHelper.getLocalDate(from.airDate),
            episodeCount = from.episodeCount ?: 0,
            id = from.id ?: -1,
            name = from.name.orEmpty(),
            overview = from.overview.orEmpty(),
            posterPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.posterPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_POSTER
                )
            ),
            seasonNumber = from.seasonNumber ?: 0
        )
    }
}