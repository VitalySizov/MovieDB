package ru.vitalysizov.moviedb.presentation.base.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.vitalysizov.moviedb.utils.*

@BindingAdapter("goneUnless")
fun View.goneUnless(visible: Boolean) {
    this.visibleOrGone(visible)
}

@BindingAdapter(value = ["loadImage"])
fun loadImage(imageView: ImageView, url: String) {
    imageView.loadImage(url)
}

@BindingAdapter(value = ["loadPersonPhoto"])
fun loadPersonPhoto(imageView: ImageView, url: String?) {
    imageView.loadPersonPhoto(url)
}

@BindingAdapter(value = ["loadPoster"])
fun loadPosterImage(imageView: ImageView, url: String) {
    imageView.loadPosterPhoto(url)
}

@BindingAdapter(value = ["loadCompanyLogo"])
fun loadCompanyLogoImage(imageView: ImageView, url: String) {
    imageView.loadCompanyLogo(url)
}