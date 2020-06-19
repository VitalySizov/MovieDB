package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_genre_details_movie.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.GenreItem

class GenreAdapterItem(
    private val genre: GenreItem,
    private val actionSearchByGenre: (id: Int) -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tvGenre.text = genre.name
            setOnClickListener { actionSearchByGenre.invoke(genre.id) }
        }
    }

    override fun getLayout(): Int = R.layout.item_genre_details_movie
}