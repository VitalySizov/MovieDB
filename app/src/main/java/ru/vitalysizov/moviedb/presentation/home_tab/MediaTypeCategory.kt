package ru.vitalysizov.moviedb.presentation.home_tab

import androidx.annotation.StringRes
import ru.vitalysizov.moviedb.R

enum class MediaTypeCategory(
    val position: Int,
    @StringRes val categoryNameId: Int
) {
    MOVIES(0, R.string.title_category_movies),
    TV(1, R.string.title_category_tv);

    companion object {
        fun getCategory(position: Int): MediaTypeCategory {
            val category = values().getOrNull(position)
            return category ?: throw Exception("Unknown category")
        }
    }
}