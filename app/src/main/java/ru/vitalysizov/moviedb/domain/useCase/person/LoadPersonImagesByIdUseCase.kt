package ru.vitalysizov.moviedb.domain.useCase.person

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IPeopleRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.ImagesMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.person.PersonImages
import javax.inject.Inject

class LoadPersonImagesByIdUseCase @Inject constructor(
    private val peopleRepository: IPeopleRepository,
    private val imagesMapper: ImagesMapper
) : SingleWithParamsUseCase<Int, PersonImages>() {

    override fun invoke(params: Int): Single<PersonImages> {
        return peopleRepository.getImagesForPerson(params).map {
            PersonImages(
                id = it.id ?: -1,
                imagesList = imagesMapper.map(it.imagesList)
            )
        }
    }
}