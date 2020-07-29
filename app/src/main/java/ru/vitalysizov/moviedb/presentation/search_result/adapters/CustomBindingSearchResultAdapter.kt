package ru.vitalysizov.moviedb.presentation.search_result.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.threeten.bp.LocalDate
import ru.vitalysizov.moviedb.R

@BindingAdapter(value = ["setCountResults"])
fun TextView.setCountResults(count: Int) {
    this.text = resources.getString(
        R.string.all_search_mask,
        count
    )
}

@BindingAdapter(value = ["setOriginalTitle", "setYear"])
fun TextView.setOriginalTitleAndYear(originalTitle: String, date: LocalDate) {
    val year: String = if (date.year == 0) {
        ""
    } else {
        date.year.toString()
    }

    // Only title or original title with year
    if (year.isEmpty()) {
        this.text = resources.getString(R.string.title_mask_usual, originalTitle)
    } else {
        this.text = resources.getString(
            R.string.title_and_year_mask_second_variant, originalTitle,
            year
        )
    }
}