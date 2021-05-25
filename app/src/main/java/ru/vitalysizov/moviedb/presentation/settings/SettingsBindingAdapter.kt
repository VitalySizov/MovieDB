package ru.vitalysizov.moviedb.presentation.settings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.domain.mapper.images.ImageQualityTypes

@BindingAdapter(value = ["setImageQualityValue"])
fun TextView.setImageQualityValue(imageQualityType: ImageQualityTypes) {
    val imageQualities = context.resources.getStringArray(R.array.image_qualities)
    this.text = imageQualities[imageQualityType.id]
}