package ru.vitalysizov.moviedb.domain.useCase.search

import com.google.gson.Gson
import io.reactivex.Single
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.domain.mapper.base.BaseItemMapper
import ru.vitalysizov.moviedb.domain.mapper.movies.MoviesMapper
import ru.vitalysizov.moviedb.domain.mapper.persons.PersonsMapper
import ru.vitalysizov.moviedb.domain.mapper.tvShows.TvShowsMapper
import ru.vitalysizov.moviedb.domain.params.search.MultiSearchParams
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.base.BaseItem
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.persons.PersonItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowItemResponse
import javax.inject.Inject

class SearchMultiByQueryUseCase @Inject constructor(
    private val gson: Gson,
    private val searchRepository: ISearchRepository,
    private val moviesMapper: MoviesMapper,
    private val tvShowsMapper: TvShowsMapper,
    private val personsMapper: PersonsMapper,
    private val baseItemMapper: BaseItemMapper<Any>
) : SingleWithParamsUseCase<MultiSearchParams, BaseItem<Any>>() {

    companion object {
        const val MEDIA_TYPE = "media_type"
        const val MOVIE_TYPE = "movie"
        const val TV_TYPE = "tv"
        const val PERSON_TYPE = "person"
    }

    override fun invoke(params: MultiSearchParams): Single<BaseItem<Any>> {
        return searchRepository.searchMulti(
            query = params.query,
            page = params.page,
            includeAdult = params.includeAdult,
            region = params.region
        ).map {
            val resultItems: ArrayList<Any> = ArrayList()

            it.result?.forEach { jsonObject ->
                if (jsonObject.get(MEDIA_TYPE).asString == MOVIE_TYPE) {
                    val movieItem: MovieItemResponse =
                        gson.fromJson(jsonObject, MovieItemResponse::class.java)
                    resultItems.add(moviesMapper.map(movieItem))
                }

                if (jsonObject.get(MEDIA_TYPE).asString == TV_TYPE) {
                    val tv: TvShowItemResponse =
                        gson.fromJson(jsonObject, TvShowItemResponse::class.java)
                    resultItems.add(tvShowsMapper.map(tv))
                }

                if (jsonObject.get(MEDIA_TYPE).asString == PERSON_TYPE) {
                    val person: PersonItemResponse =
                        gson.fromJson(jsonObject, PersonItemResponse::class.java)
                    resultItems.add(personsMapper.map(person))
                }
            }

            baseItemMapper.map(
                BaseResponse(
                    page = it.page,
                    result = resultItems,
                    totalResults = it.totalResults,
                    totalPages = it.totalPages,
                    dates = it.dates
                )
            )
        }
    }
}