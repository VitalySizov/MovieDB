package ru.vitalysizov.moviedb.di.modules

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.remote.network.web.MovieDbWebService
import ru.vitalysizov.moviedb.data.repo.*
import ru.vitalysizov.moviedb.data.repo.impl.*
import ru.vitalysizov.moviedb.data.repo.web.MovieDbWebRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(apiService: IMovieDbApiService): IMoviesRepository {
        return MoviesRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideTrendingRepository(apiService: IMovieDbApiService): ITrendingRepository {
        return TrendingRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: IMovieDbApiService): ISearchRepository {
        return SearchRepository(apiService)
    }

    @Provides
    @Singleton
    fun providePeopleRepository(apiService: IMovieDbApiService): IPeopleRepository {
        return PeopleRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(apiService: IMovieDbApiService): IAuthenticationRepository {
        return AuthenticationRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(apiService: IMovieDbApiService): IAccountRepository {
        return AccountRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideMovieDbWebRepository(): MovieDbWebService {
        return MovieDbWebRepository()
    }

    @Provides
    @Singleton
    fun provideConfigurationRepository(apiService: IMovieDbApiService): ConfigurationRepository {
        return ConfigurationRepository(apiService)
    }
}