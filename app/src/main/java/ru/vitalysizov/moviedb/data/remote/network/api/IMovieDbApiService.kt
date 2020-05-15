package ru.vitalysizov.moviedb.data.remote.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenresResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieResponse

interface IMovieDbApiService {

    @GET("movie/now_playing")
    fun loadNowPlayingMovies(): Single<BaseResponse<MovieResponse>>

    @GET("movie/popular")
    fun loadPopularMovies(): Single<BaseResponse<MovieResponse>>

    @GET("genre/movie/list")
    fun loadMoviesGenres(): Single<GenresResponse>

    @GET("trending/{mediaType}/{timeWindow}")
    fun loadTrendingItems(
        @Path("mediaType") mediaType: String,
        @Path("timeWindow") timeWindow: String
    ): Single<BaseResponse<MovieResponse>>

}