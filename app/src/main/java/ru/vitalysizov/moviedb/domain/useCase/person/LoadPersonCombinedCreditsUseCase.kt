package ru.vitalysizov.moviedb.domain.useCase.person

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.IPeopleRepository
import ru.vitalysizov.moviedb.domain.mapper.people.PersonCastMovieItemMapper
import ru.vitalysizov.moviedb.domain.mapper.people.PersonCastTvItemMapper
import ru.vitalysizov.moviedb.domain.mapper.people.PersonCrewItemMapper
import ru.vitalysizov.moviedb.domain.params.person.PersonCombinedCreditsParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.person.PersonCastMovieItem
import ru.vitalysizov.moviedb.model.domain.person.PersonCombinedCredits
import javax.inject.Inject

class LoadPersonCombinedCreditsUseCase @Inject constructor(
    private val peopleRepository: IPeopleRepository,
    private val personCastMovieItemMapper: PersonCastMovieItemMapper,
    private val personCastTvItemMapper: PersonCastTvItemMapper,
    private val personCrewItemMapper: PersonCrewItemMapper
) : SingleWithParamsUseCase<PersonCombinedCreditsParams, PersonCombinedCredits>() {

    companion object {
        const val MOVIE_MEDIA_TYPE = "movie"
        const val TV_MEDIA_TYPE = "tv"
    }

    override fun invoke(params: PersonCombinedCreditsParams): Single<PersonCombinedCredits> {
        return peopleRepository.getPersonCombinedCredits(params.personId).map { it ->

            val cast = ArrayList<Any>()
            val crew = it.crew?.map { crew -> personCrewItemMapper.map(crew) } ?: emptyList()

            it.cast?.forEach {
                if (it.mediaType == MOVIE_MEDIA_TYPE) {
                    cast.add(personCastMovieItemMapper.map(it))
                } else if (it.mediaType == TV_MEDIA_TYPE) {
                    cast.add(personCastTvItemMapper.map(it))
                }
            }


            cast.sortByDescending { it is PersonCastMovieItem  }

            //TODO add sortedByDate
            PersonCombinedCredits(
                cast = if (params.sortedByDescendingReleaseDate) {
                    cast
                } else {
                    cast
                },
                crew = if (params.sortedByDescendingReleaseDate) {
                    crew.sortedByDescending { it.releaseDate }
                } else {
                    crew
                },
                id = it.id ?: -1
            )
        }
    }

}