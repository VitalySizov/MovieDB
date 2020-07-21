package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.utils.loadImage

@BindingAdapter(value = ["setMovies", "setMovieListener"])
fun RecyclerView.setRowMovies(movies: List<MovieItem>, listener: ItemClickListener<MovieItem>) {
    val movieAdapter = MovieAdapter(listener)
    movieAdapter.submitList(movies)
    adapter = movieAdapter
}

@BindingAdapter(value = ["posterImage"])
fun loadImage(imageView: ImageView, url: String) {
    imageView.loadImage(url)
}

@BindingAdapter(value = ["setGenres"])
fun RecyclerView.setRowGenres(genres: List<GenreItem>) {
    val genreAdapter = GenreAdapter()
    genreAdapter.submitList(genres)
    adapter = genreAdapter
}

@BindingAdapter(value = ["setTrendingMovies", "setTrendingMovieListener"])
fun RecyclerView.setRowTrendingMovies(
    movies: List<MovieItem>,
    listener: ItemClickListener<MovieItem>
) {
    val movieTrendingAdapter = MovieTrendingAdapter(listener)
    movieTrendingAdapter.submitList(movies)
    adapter = movieTrendingAdapter
}