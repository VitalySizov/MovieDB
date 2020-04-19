package ru.vitalysizov.moviedb.di.modules

import android.app.Application
import com.blankj.utilcode.util.Utils
import dagger.Module

@Module
class UtilsModule(app: Application) {

    init {
        Utils.init(app)
    }
}