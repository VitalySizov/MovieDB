package ru.vitalysizov.moviedb.data.remote.network.api

import io.reactivex.Single
import retrofit2.http.GET
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse

interface IMovieDbApiService {

    @GET("movie/now_playing")
    fun loadNowPlayingMovies(): Single<BaseResponse<MovieResponse>>

}