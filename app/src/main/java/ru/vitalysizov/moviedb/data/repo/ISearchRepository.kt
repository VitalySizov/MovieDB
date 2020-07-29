package ru.vitalysizov.moviedb.data.repo

import com.google.gson.JsonObject
import io.reactivex.Single
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.collections.CollectionItemResponse
import ru.vitalysizov.moviedb.model.network.responses.companies.CompanyItemResponse
import ru.vitalysizov.moviedb.model.network.responses.keywords.KeywordItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.persons.PersonItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowItemResponse

interface ISearchRepository {

    fun searchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean,
        region: String,
        year: Int,
        primaryReleaseYear: Int
    ): Single<BaseResponse<MovieItemResponse>>

    fun searchPersons(
        query: String,
        page: Int,
        includeAdult: Boolean,
        region: String
    ): Single<BaseResponse<PersonItemResponse>>

    fun searchCompanies(
        query: String,
        page: Int
    ): Single<BaseResponse<CompanyItemResponse>>

    fun searchTvShows(
        query: String,
        page: Int,
        includeAdult: Boolean,
        firstAirDateYear: Int
    ): Single<BaseResponse<TvShowItemResponse>>

    fun searchCollections(
        query: String,
        page: Int
    ): Single<BaseResponse<CollectionItemResponse>>

    fun searchKeywords(
        query: String,
        page: Int
    ): Single<BaseResponse<KeywordItemResponse>>

    fun searchMulti(
        query: String,
        page: Int,
        includeAdult: Boolean,
        region: String
    ): Single<BaseResponse<JsonObject>>
}