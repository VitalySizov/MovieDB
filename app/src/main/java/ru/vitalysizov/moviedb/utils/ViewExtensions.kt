package ru.vitalysizov.moviedb.utils

import android.widget.ImageView
import coil.api.load
import ru.vitalysizov.moviedb.BuildConfig

const val BASE_IMG_URL = BuildConfig.BASE_IMG_URL

fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }

    this.load("$BASE_IMG_URL$url")
}