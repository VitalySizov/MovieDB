package ru.vitalysizov.moviedb.data.repo.impl

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.ISearchRepository
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.collections.CollectionItemResponse
import ru.vitalysizov.moviedb.model.network.responses.companies.CompanyItemResponse
import ru.vitalysizov.moviedb.model.network.responses.keywords.KeywordItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.persons.PersonItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowItemResponse

class SearchRepository(
    private val apiService: IMovieDbApiService
) : ISearchRepository {

    override fun searchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean,
        region: String,
        year: Int,
        primaryReleaseYear: Int
    ): Single<BaseResponse<MovieItemResponse>> {
        return apiService.searchMovies(
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region,
            year = year,
            primaryReleaseYear = primaryReleaseYear
        )
    }

    override fun searchPersons(
        query: String,
        page: Int,
        includeAdult: Boolean,
        region: String
    ): Single<BaseResponse<PersonItemResponse>> {
        return apiService.searchPersons(
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region
        )
    }

    override fun searchCompanies(
        query: String,
        page: Int
    ): Single<BaseResponse<CompanyItemResponse>> {
        return apiService.searchCompanies(
            query = query,
            page = page
        )
    }

    override fun searchTvShows(
        query: String,
        page: Int,
        includeAdult: Boolean,
        firstAirDateYear: Int
    ): Single<BaseResponse<TvShowItemResponse>> {
        return apiService.searchTvShow(
            query = query,
            page = page,
            includeAdult = includeAdult,
            firstAirDateYear = firstAirDateYear
        )
    }

    override fun searchCollections(
        query: String,
        page: Int
    ): Single<BaseResponse<CollectionItemResponse>> {
        return apiService.searchCollection(
            query = query,
            page = page
        )
    }

    override fun searchKeywords(
        query: String,
        page: Int
    ): Single<BaseResponse<KeywordItemResponse>> {
        return apiService.searchKeyWord(
            query = query,
            page = page
        )
    }

    override fun searchMulti(
        query: String,
        page: Int,
        includeAdult: Boolean,
        region: String
    ): Single<BaseResponse<JsonObject>> {
        return apiService.searchMulti(
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region
        )
    }
}