package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IPeopleRepository
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleDetailsResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCombinedCreditsResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PersonExternalIdsResponse
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val apiService: IMovieDbApiService
) : IPeopleRepository {

    override fun getPeopleDetails(personId: Int): Single<PeopleDetailsResponse> {
        return apiService.getPersonDetails(personId)
    }

    override fun getPersonCombinedCredits(personId: Int): Single<PersonCombinedCreditsResponse> {
        return apiService.getPersonCombinedCredits(personId)
    }

    override fun getExternalIdsSourceForPerson(personId: Int): Single<PersonExternalIdsResponse> {
        return apiService.getExternalIdsSourceForPerson(personId)
    }

    override fun getImagesForPerson(personId: Int): Single<PeopleImagesResponse> {
        return apiService.getImagesForPerson(personId)
    }
}