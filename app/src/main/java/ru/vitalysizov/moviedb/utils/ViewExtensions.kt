package ru.vitalysizov.moviedb.utils

import android.view.View
import android.widget.ImageView
import coil.api.load
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.R

const val BASE_IMG_URL = BuildConfig.BASE_IMG_URL

fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }
    this.load("$BASE_IMG_URL$url")
}

fun ImageView.loadCastPhoto(url: String?) {
    if (url.isNullOrEmpty()) {
        this.load(R.drawable.ic_baseline_person_24)
    } else {
        this.load("$BASE_IMG_URL$url")
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleOrGone(show: Boolean) {
    if (show) {
        visible()
    } else {
        gone()
    }
}