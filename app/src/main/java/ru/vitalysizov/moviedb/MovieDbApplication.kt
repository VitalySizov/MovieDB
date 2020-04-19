package ru.vitalysizov.moviedb

import android.app.Application
import ru.vitalysizov.moviedb.di.components.AppComponent
import ru.vitalysizov.moviedb.di.components.DaggerAppComponent
import ru.vitalysizov.moviedb.di.modules.AppModule
import ru.vitalysizov.moviedb.di.modules.RepositoriesModule

class MovieDbApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        appComponent.inject(this)
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .repositoriesModule(RepositoriesModule())
            .build()
    }
}