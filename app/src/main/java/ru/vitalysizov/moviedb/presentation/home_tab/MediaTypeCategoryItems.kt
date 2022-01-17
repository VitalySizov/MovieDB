package ru.vitalysizov.moviedb.presentation.home_tab

import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem

sealed class MediaTypeCategoryItems {
    class Movies(val items: List<MovieItem>) : MediaTypeCategoryItems()
    class TvShow(val items: List<TvShowItem>) : MediaTypeCategoryItems()
}