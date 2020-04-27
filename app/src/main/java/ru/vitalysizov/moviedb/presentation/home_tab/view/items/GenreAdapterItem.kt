package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_genre.view.*
import ru.vitalysizov.moviedb.R

class GenreAdapterItem(private val genreName: String) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_genre_name.text = genreName
        }
    }

    override fun getLayout(): Int = R.layout.item_genre
}