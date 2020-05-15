package ru.vitalysizov.moviedb

import android.content.Context
import com.blankj.utilcode.util.Utils
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.vitalysizov.moviedb.di.components.DaggerAppComponent

class App : DaggerApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Utils.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}