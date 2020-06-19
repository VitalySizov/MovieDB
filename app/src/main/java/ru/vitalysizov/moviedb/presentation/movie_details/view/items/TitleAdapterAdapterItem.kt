package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_title_movie_details.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem

class TitleAdapterAdapterItem(private val movieDetails: MovieDetailsItem) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {

            tvLocalTitle.text = movieDetails.title

            tvOriginalTitleAndYear.text = resources.getString(
                R.string.title_and_year_mask,
                movieDetails.originalTitle,
                movieDetails.releaseDate.year.toString()
            )

            tvProductionCountries.text = movieDetails.productionCountries.joinToString { it.name }

            tvRunTime.text = resources.getString(
                R.string.hours_and_minutes_mask,
                movieDetails.runTime.hour,
                movieDetails.runTime.minute
            )

            tvTagLine.text = movieDetails.tagLine
        }
    }

    override fun getLayout(): Int = R.layout.item_title_movie_details

}