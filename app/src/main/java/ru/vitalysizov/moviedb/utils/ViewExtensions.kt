package ru.vitalysizov.moviedb.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun ImageView.loadPersonPhoto(url: String?) {
    if (url.isNullOrEmpty()) {
        this.load(R.drawable.ic_baseline_person_24)
    } else {
        this.load("$BASE_IMG_URL$url")
    }
}

fun ImageView.loadPosterPhoto(url: String?) {
    if (url.isNullOrEmpty()) {
        this.load(R.drawable.ic_baseline_local_movies_24)
    } else {
        this.load("$BASE_IMG_URL$url")
    }
}

fun ImageView.loadCompanyLogo(url: String?) {
    if (url.isNullOrEmpty()) {
        this.load(R.drawable.ic_baseline_location_city_24)
    } else {
        this.load("$BASE_IMG_URL$url")
    }
}

fun dismissKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
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