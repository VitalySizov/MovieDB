package ru.vitalysizov.moviedb.domain.useCase.person

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IPeopleRepository
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.person.PersonExternalIds
import javax.inject.Inject

class LoadExternalIdsForPerson @Inject constructor(
    private val peopleRepository: IPeopleRepository
) : SingleWithParamsUseCase<Int, PersonExternalIds>() {
    override fun invoke(params: Int): Single<PersonExternalIds> {
        return peopleRepository.getExternalIdsSourceForPerson(params).map {
            PersonExternalIds(
                id = it.id ?: -1,
                twitterId = it.twitterId.orEmpty(),
                faceBookId = it.faceBookId.orEmpty(),
                freebaseId = it.freebaseId.orEmpty(),
                instagramId = it.instagramId.orEmpty(),
                tvRageId = it.tvRageId ?: -1,
                freebaseMid = it.freebaseMid.orEmpty(),
                imDbId = it.imDbId.orEmpty()
            )
        }
    }
}