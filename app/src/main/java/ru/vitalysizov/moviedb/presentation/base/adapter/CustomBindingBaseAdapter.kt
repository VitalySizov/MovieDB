package ru.vitalysizov.moviedb.presentation.base.adapter

import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ru.vitalysizov.moviedb.utils.gone
import ru.vitalysizov.moviedb.utils.loadImage
import ru.vitalysizov.moviedb.utils.visible

@BindingAdapter(value = ["showLoading"])
fun FrameLayout.showLoading(state: Boolean) {
    if (state) {
        this.visible()
    } else {
        this.gone()
    }
}

@BindingAdapter(value = ["loadImage"])
fun loadImage(imageView: ImageView, url: String) {
    imageView.loadImage(url)
}

