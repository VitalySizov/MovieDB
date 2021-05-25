package ru.vitalysizov.moviedb.presentation.rated.content

import ru.vitalysizov.moviedb.model.domain.movies.RatedMovieItem
import ru.vitalysizov.moviedb.model.domain.tvEpisodes.RatedTvEpisodeItem
import ru.vitalysizov.moviedb.model.domain.tvShows.RatedTvShowItem
import java.io.Serializable

data class RatedScreenContent(
    val movies: List<RatedMovieItem>,
    val tvShows: List<RatedTvShowItem>,
    val tvEpisodes: List<RatedTvEpisodeItem>
): Serializable