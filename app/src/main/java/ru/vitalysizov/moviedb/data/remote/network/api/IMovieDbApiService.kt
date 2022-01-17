package ru.vitalysizov.moviedb.data.remote.network.api

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.*
import ru.vitalysizov.moviedb.domain.params.authentication.CreateSessionParams
import ru.vitalysizov.moviedb.domain.params.authentication.DeleteSessionParams
import ru.vitalysizov.moviedb.model.network.responses.account.AccountDetailsResponse
import ru.vitalysizov.moviedb.model.network.responses.authentication.LogoutResponse
import ru.vitalysizov.moviedb.model.network.responses.authentication.RequestTokenResponse
import ru.vitalysizov.moviedb.model.network.responses.authentication.SessionResponse
import ru.vitalysizov.moviedb.model.network.responses.base.BaseResponse
import ru.vitalysizov.moviedb.model.network.responses.castAndCrew.CastAndCrewResponse
import ru.vitalysizov.moviedb.model.network.responses.collections.CollectionItemResponse
import ru.vitalysizov.moviedb.model.network.responses.companies.CompanyItemResponse
import ru.vitalysizov.moviedb.model.network.responses.configuration.ConfigurationResponse
import ru.vitalysizov.moviedb.model.network.responses.genres.GenresResponse
import ru.vitalysizov.moviedb.model.network.responses.keywords.KeywordItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieDetailsItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.MovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.movies.RatedMovieItemResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleDetailsResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PeopleImagesResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PersonCombinedCreditsResponse
import ru.vitalysizov.moviedb.model.network.responses.people.PersonExternalIdsResponse
import ru.vitalysizov.moviedb.model.network.responses.persons.PersonItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvEpisodes.RatedTvEpisodeItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.ContentRatingsResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.RatedTvShowItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowDetailsItemResponse
import ru.vitalysizov.moviedb.model.network.responses.tvShows.TvShowItemResponse

interface IMovieDbApiService {

    /**
     * Movies
     */
    @GET("movie/now_playing")
    fun loadNowPlayingMovies(): Single<BaseResponse<MovieItemResponse>>

    @GET("movie/popular")
    fun loadPopularMovies(): Single<BaseResponse<MovieItemResponse>>

    /**
     * Genres
     */
    @GET("genre/movie/list")
    fun loadMoviesGenres(): Single<GenresResponse>

    /**
     * Trending
     */
    @GET("trending/{mediaType}/{timeWindow}")
    fun loadTrendingItems(
        @Path("mediaType") mediaType: String,
        @Path("timeWindow") timeWindow: String
    ): Single<BaseResponse<MovieItemResponse>>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<BaseResponse<MovieItemResponse>>

    /**
     * Movie details
     */
    @GET("movie/{movie_id}")
    fun loadMovieDetails(
        @Path("movie_id") movieId: Int
    ): Single<MovieDetailsItemResponse>

    @GET("movie/{movie_id}/images")
    fun loadMovieImages(
        @Path("movie_id") movieId: Int
    ): Single<MovieImagesResponse>

    @GET("movie/{movie_id}/credits")
    fun loadCastAndCrew(
        @Path("movie_id") movieId: Int
    ): Single<CastAndCrewResponse>

    /**
     * Search
     */
    @GET("search/movie")
    fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean,
        @Query("region") region: String,
        @Query("year") year: Int,
        @Query("primary_release_year") primaryReleaseYear: Int
    ): Single<BaseResponse<MovieItemResponse>>

    @GET("search/person")
    fun searchPersons(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean,
        @Query("region") region: String
    ): Single<BaseResponse<PersonItemResponse>>

    @GET("search/company")
    fun searchCompanies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<BaseResponse<CompanyItemResponse>>

    @GET("search/tv")
    fun searchTvShow(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean,
        @Query("first_air_date_year") firstAirDateYear: Int
    ): Single<BaseResponse<TvShowItemResponse>>

    @GET("search/collection")
    fun searchCollection(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<BaseResponse<CollectionItemResponse>>

    @GET("search/keyword")
    fun searchKeyWord(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<BaseResponse<KeywordItemResponse>>

    @GET("search/multi")
    fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean,
        @Query("region") region: String
    ): Single<BaseResponse<JsonObject>>

    /**
     * Get the primary person details by id.
     */
    @GET("person/{person_id}")
    fun getPersonDetails(@Path("person_id") personId: Int): Single<PeopleDetailsResponse>

    /**
     * Get the movie and TV credits together in a single response.
     */
    @GET("person/{person_id}/combined_credits")
    fun getPersonCombinedCredits(@Path("person_id") personId: Int): Single<PersonCombinedCreditsResponse>

    /**
     * Get the external ids for a person. We currently support the following external sources.
     */
    @GET("person/{person_id}/external_ids")
    fun getExternalIdsSourceForPerson(@Path("person_id") personId: Int): Single<PersonExternalIdsResponse>

    /**
     * Get the images for a person.
     */
    @GET("person/{person_id}/images")
    fun getImagesForPerson(@Path("person_id") personId: Int): Single<PeopleImagesResponse>

    @GET("authentication/token/new")
    fun createRequestToken(): Single<RequestTokenResponse>

    @POST("authentication/session/new")
    fun createSession(@Body createSessionParams: CreateSessionParams): Single<SessionResponse>

    @GET("account")
    fun getAccountDetails(@Query("session_id") sessionId: String): Single<AccountDetailsResponse>

    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    fun logoutAccount(@Body deleteSessionParams: DeleteSessionParams): Single<LogoutResponse>

    @GET("configuration")
    fun getConfiguration(): Single<ConfigurationResponse>

    @GET("account/{account_id}/rated/movies")
    fun getRatedMovies(
        @Path("account_id") accountId: String,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): Single<BaseResponse<RatedMovieItemResponse>>

    @GET("account/{account_id}/rated/tv")
    fun getRatedTvShow(
        @Path("account_id") accountId: String,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): Single<BaseResponse<RatedTvShowItemResponse>>

    @GET("account/{account_id}/rated/tv/episodes")
    fun getRatedTvEpisodes(
        @Path("account_id") accountId: String,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): Single<BaseResponse<RatedTvEpisodeItemResponse>>

    @GET("tv/{tv_id}")
    fun getTvShowDetailsById(
        @Path("tv_id") tvId: Int,
    ): Single<TvShowDetailsItemResponse>

    @GET("tv/{tv_id}/images")
    fun getTvShowImages(
        @Path("tv_id") tvId: Int
    ): Single<MovieImagesResponse>

    @GET("tv/{tv_id}/credits")
    fun getTvShowCredits(
        @Path("tv_id") tvId: Int
    ): Single<CastAndCrewResponse>

    @GET("tv/{tv_id}/content_ratings")
    fun getTvShowContentRatings(
        @Path("tv_id") tvId: Int
    ): Single<ContentRatingsResponse>

    @GET("tv/popular")
    fun getTvShowPopular(): Single<BaseResponse<TvShowItemResponse>>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(): Single<BaseResponse<TvShowItemResponse>>

}