package ru.vitalysizov.moviedb.data.repo

import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleDetailsResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCombinedCreditsResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PersonExternalIdsResponse

interface IPeopleRepository {

    fun getPeopleDetails(personId: Int): Single<PeopleDetailsResponse>

    fun getPersonCombinedCredits(personId: Int): Single<PersonCombinedCreditsResponse>

    fun getExternalIdsSourceForPerson(personId: Int): Single<PersonExternalIdsResponse>

    fun getImagesForPerson(personId: Int): Single<PeopleImagesResponse>

}