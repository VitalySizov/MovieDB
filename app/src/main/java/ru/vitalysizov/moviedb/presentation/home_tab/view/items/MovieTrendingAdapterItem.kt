package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import android.widget.ImageView
import android.widget.TextView
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie_trending.view.*

import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.utils.loadImage

class MovieTrendingAdapterItem(private val movie: MovieItem) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            bindMoviePoster(iv_movie_poster)
            bindMovieTitle(tv_movie_title)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie_trending

    private fun bindMoviePoster(imageView: ImageView) {
        imageView.loadImage(movie.backDropPath)
    }

    private fun bindMovieTitle(textView: TextView) {
        textView.text = movie.title
    }
}