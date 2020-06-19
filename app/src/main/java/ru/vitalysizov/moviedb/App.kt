package ru.vitalysizov.moviedb

import android.content.Context
import com.blankj.utilcode.util.Utils
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.vitalysizov.moviedb.di.components.DaggerAppComponent

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initAndroidThreeTen()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Utils.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun initAndroidThreeTen() {
        AndroidThreeTen.init(this)
    }

}