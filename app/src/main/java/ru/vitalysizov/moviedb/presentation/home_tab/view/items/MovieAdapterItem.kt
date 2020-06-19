package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.utils.loadImage

class MovieAdapterItem(
    private val movie: MovieItem,
    private val actionDetails: (id: Int) -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            iv_movie_poster.loadImage(movie.posterPath)
            tv_movie_title.text = movie.title
            setOnClickListener { actionDetails.invoke(movie.id) }
        }
    }

    override fun getLayout(): Int = R.layout.item_movie
}