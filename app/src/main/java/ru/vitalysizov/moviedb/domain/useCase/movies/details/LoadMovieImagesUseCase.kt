package ru.vitalysizov.moviedb.domain.useCase.movies.details

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.domain.mapper.images.ImageTypes
import ru.vitalysizov.moviedb.domain.mapper.images.ImagesMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.movies.MovieImages
import javax.inject.Inject

class LoadMovieImagesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val imagesMapper: ImagesMapper
) : SingleWithParamsUseCase<Int, MovieImages>() {

    override fun invoke(params: Int): Single<MovieImages> {
        return moviesRepository.loadMovieImages(params).map {
            MovieImages(
                id = it.id ?: 0,
                backdrops = imagesMapper.map(Pair(it.backdrops, ImageTypes.IMAGE_BACKDROP)),
                posters = imagesMapper.map(
                    Pair(it.posters, ImageTypes.IMAGE_POSTER)
                )
            )
        }
    }
}