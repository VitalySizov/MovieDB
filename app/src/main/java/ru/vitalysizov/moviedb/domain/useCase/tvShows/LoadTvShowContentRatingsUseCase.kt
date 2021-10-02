package ru.vitalysizov.moviedb.domain.useCase.tvShows

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.tvShows.ContentRatingItem
import ru.vitalysizov.moviedb.model.domain.tvShows.ContentRatingsItem
import javax.inject.Inject

class LoadTvShowContentRatingsUseCase @Inject constructor(
    private val tvShowRepository: ITvShowsRepository
) : SingleWithParamsUseCase<Int, ContentRatingsItem>() {
    override fun invoke(params: Int): Single<ContentRatingsItem> {
        return tvShowRepository.getTvShowContentRatings(params).map {
            ContentRatingsItem(
                results = it.results?.map {
                    ContentRatingItem(
                        iso3166_1 = it.iso3166_1.orEmpty(),
                        rating = it.rating.orEmpty()
                    )
                } ?: emptyList(),
                id = it.id ?: -1
            )
        }
    }
}