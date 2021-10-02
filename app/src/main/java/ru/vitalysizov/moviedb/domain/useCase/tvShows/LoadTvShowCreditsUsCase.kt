package ru.vitalysizov.moviedb.domain.useCase.tvShows

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ITvShowsRepository
import ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew.CastMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.castAndCrew.CrewMapper
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastAndCrewItem
import javax.inject.Inject

class LoadTvShowCreditsUsCase @Inject constructor(
    private val tvShowRepository: ITvShowsRepository,
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper
) : SingleWithParamsUseCase<Int, CastAndCrewItem>() {

    override fun invoke(params: Int): Single<CastAndCrewItem> {
        return tvShowRepository.getTvShowCredits(params).map {
            CastAndCrewItem(
                id = it.id ?: -1,
                cast = castMapper.map(it.cast),
                crew = crewMapper.map(it.crew)
            )
        }
    }
}