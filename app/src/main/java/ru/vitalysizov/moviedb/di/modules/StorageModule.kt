package ru.vitalysizov.moviedb.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.data.local.prefs.MovieDbPreferences
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideSharePreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideMovieDbPreferences(sharedPreferences: SharedPreferences, gson: Gson): MovieDbPreferences {
        return MovieDbPreferences(sharedPreferences, gson)
    }
}