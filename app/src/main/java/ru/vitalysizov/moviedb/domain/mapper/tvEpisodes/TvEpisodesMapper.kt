package ru.vitalysizov.moviedb.domain.mapper.tvEpisodes

import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImageUrlMapper
import ru.vitalysizov.moviedb.domain.mapper.images.UrlPathAndType
import ru.vitalysizov.moviedb.model.domain.tvEpisodes.RatedTvEpisodeItem
import ru.vitalysizov.moviedb.model.network.responses.tvEpisodes.RatedTvEpisodeItemResponse
import javax.inject.Inject

class TvEpisodesMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<RatedTvEpisodeItemResponse, RatedTvEpisodeItem> {
    override fun map(from: RatedTvEpisodeItemResponse): RatedTvEpisodeItem {
        return RatedTvEpisodeItem(
            airDate = if (!from.airDate.isNullOrEmpty()) {
                LocalDate.parse(from.airDate)
            } else {
                LocalDate.of(0, 1, 1)
            },
            episodeNumber = from.episodeNumber ?: 0,
            id = from.id ?: -1,
            name = from.name.orEmpty(),
            overview = from.overview.orEmpty(),
            productionCode = from.productionCode.orEmpty(),
            seasonNumber = from.seasonNumber ?: 0,
            show_id = from.show_id ?: -1,
            stillPath = imageUrlMapper.map(
                UrlPathAndType(
                    path = from.stillPath.orEmpty(),
                    imageType = ImageTypes.IMAGE_STILL
                )
            ),
            voteAverage = from.voteAverage ?: 0.0,
            voteCount = from.voteCount ?: 0,
            rating = from.rating ?: 0
        )
    }

}