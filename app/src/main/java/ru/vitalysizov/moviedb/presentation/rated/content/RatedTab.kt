package ru.vitalysizov.moviedb.presentation.rated.content

import androidx.annotation.StringRes
import ru.vitalysizov.moviedb.R
import java.io.Serializable
import java.lang.Exception

enum class RatedTab(@StringRes val tabName: Int) : Serializable {
    MOVIES(R.string.movies),
    TV_SHOWS(R.string.tv_shows),
    TV_EPISODES(R.string.tv_episodes);

    companion object {
        fun getTabByPosition(position: Int): RatedTab {
            val tab = values().getOrNull(position)
            return tab ?: throw Exception("Unknown tab position")
        }
    }
}