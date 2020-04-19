package ru.vitalysizov.moviedb.di.modules

import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.repo.IMoviesRepository
import ru.vitalysizov.moviedb.data.repo.impl.MoviesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(apiService: IMovieDbApiService): IMoviesRepository {
        return MoviesRepository(apiService)
    }
}