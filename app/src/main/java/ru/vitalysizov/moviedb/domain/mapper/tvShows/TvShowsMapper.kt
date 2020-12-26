package ru.vitalysizov.moviedb.domain.mapper.tvShows

import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowItemResponse
import javax.inject.Inject

class TvShowsMapper @Inject constructor() : Mapper<TvShowItemResponse, TvShowItem> {
    override fun map(from: TvShowItemResponse): TvShowItem {
        return TvShowItem(
            posterPath = from.posterPath.orEmpty(),
            popularity = from.popularity ?: 0.0,
            id = from.id ?: -1,
            backdropPath = from.backdropPath.orEmpty(),
            voteAverage = from.voteAverage.toString(),
            overview = from.overview.orEmpty(),
            firstAirDate = if (!from.firstAirDate.isNullOrEmpty()) {
                LocalDate.parse(from.firstAirDate)
            } else {
                LocalDate.of(0, 1, 1)
            },
            originCountry = from.originCountry?.map { it } ?: listOf(),
            genreIds = from.genreIds?.map { it } ?: listOf(),
            originalLanguage = from.originalLanguage.orEmpty(),
            voteCount = from.voteCount ?: 0,
            name = from.name.orEmpty(),
            originalName = from.originalName.orEmpty()
        )
    }
}