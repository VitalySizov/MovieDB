package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_backdrop_movie_details.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.loadImage

class BackdropAdapterItem(private val imageUrl: String) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            iv_backdrop.loadImage(imageUrl)
        }
    }

    override fun getLayout(): Int  = R.layout.item_backdrop_movie_details
}