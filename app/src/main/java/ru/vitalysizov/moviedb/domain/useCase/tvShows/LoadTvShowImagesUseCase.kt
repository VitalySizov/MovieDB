package ru.vitalysizov.moviedb.domain.useCase.tvShows

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImagesMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowImages
import javax.inject.Inject

class LoadTvShowImagesUseCase @Inject constructor(
    private val tvShowRepository: ITvShowsRepository,
    private val imagesMapper: ImagesMapper
) : SingleWithParamsUseCase<Int, TvShowImages>() {

    override fun invoke(params: Int): Single<TvShowImages> {
        return tvShowRepository.getTvShowImages(params).map {
            TvShowImages(
                id = it.id ?: 0,
                backdrops = imagesMapper.map(Pair(it.backdrops, ImageTypes.IMAGE_BACKDROP)),
                posters = imagesMapper.map(Pair(it.posters, ImageTypes.IMAGE_POSTER))
            )
        }
    }
}