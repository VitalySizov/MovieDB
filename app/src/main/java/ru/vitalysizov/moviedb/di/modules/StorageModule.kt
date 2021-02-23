package ru.vitalysizov.moviedb.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.vitalysizov.moviedb.data.local.prefs.AccountPreferences
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
    fun provideAccountPreferences(sharedPreferences: SharedPreferences): AccountPreferences {
        return AccountPreferences(sharedPreferences)
    }
}