package ru.vitalysizov.moviedb

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.vitalysizov.moviedb.di.components.DaggerAppComponent

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initAndroidThreeTen()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun initAndroidThreeTen() {
        AndroidThreeTen.init(this)
    }

}