package ru.vitalysizov.moviedb.domain.mapper.tvShows

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.tvShows.EpisodeToAirItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.EpisodeToAirResponse
import ru.vitalysizov.moviedb.utils.DateHelper
import javax.inject.Inject

class EpisodeToAirMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<EpisodeToAirResponse?, EpisodeToAirItem> {
    override fun map(from: EpisodeToAirResponse?): EpisodeToAirItem {
        when (from) {
            null -> {
                return EpisodeToAirItem(
                    airDate = DateHelper.getLocalDate(""),
                    episodeNumber = 0,
                    id = -1,
                    name = "",
                    overview = "",
                    productionCode = "",
                    seasonNumber = -1,
                    still_path = "",
                    voteAverage = "0.0",
                    voteCount = 0
                )
            }
            else -> {
                return EpisodeToAirItem(
                    airDate = DateHelper.getLocalDate(from.airDate),
                    episodeNumber = from.episodeNumber ?: 0,
                    id = from.id ?: -1,
                    name = from.name.orEmpty(),
                    overview = from.overview.orEmpty(),
                    productionCode = from.productionCode.orEmpty(),
                    seasonNumber = from.seasonNumber ?: 1,
                    still_path = imageUrlMapper.map(
                        UrlPathAndType(
                            path = from.still_path.orEmpty(),
                            imageType = ImageTypes.IMAGE_STILL
                        )
                    ),
                    voteAverage = from.voteAverage?.toString() ?: "0.0",
                    voteCount = from.voteCount ?: 0
                )
            }
        }
    }
}