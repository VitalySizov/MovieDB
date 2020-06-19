package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_poster_and_description.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.utils.GroupieAdapter
import ru.vitalysizov.moviedb.utils.loadImage

class PosterAndDescriptionAdapterItem(
    private val movieDetailsItem: MovieDetailsItem,
    private val genresItems: List<Item>
) : Item() {

    private val carouselGenres = GroupieAdapter()

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val layoutManager = LinearLayoutManager(
            viewHolder.itemView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        viewHolder.itemView.apply {
            ivPoster.loadImage(movieDetailsItem.posterPath)
            rvGenresMovieDetails.layoutManager = layoutManager
            rvGenresMovieDetails.adapter = carouselGenres


            carouselGenres.addAll(genresItems)
            tvDescriptionMovie.text = movieDetailsItem.overview
        }
    }

    override fun getLayout(): Int = R.layout.item_poster_and_description
}