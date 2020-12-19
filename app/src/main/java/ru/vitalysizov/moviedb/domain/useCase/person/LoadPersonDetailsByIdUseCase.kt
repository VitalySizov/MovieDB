package ru.vitalysizov.moviedb.domain.useCase.person

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IPeopleRepository
import ru.vitalysizov.moviedb.domain.mapper.people.PeopleDetailsMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.person.PersonDetails
import javax.inject.Inject

class LoadPersonDetailsByIdUseCase @Inject constructor(
    private val peopleRepository: IPeopleRepository,
    private val peopleDetailsMapper: PeopleDetailsMapper
) : SingleWithParamsUseCase<Int, PersonDetails>() {
    override fun invoke(params: Int): Single<PersonDetails> {
        return peopleRepository.getPeopleDetails(params).map {
            peopleDetailsMapper.map(it)
        }
    }
}