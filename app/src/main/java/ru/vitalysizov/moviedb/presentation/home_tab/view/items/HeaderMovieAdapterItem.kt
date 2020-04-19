package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_header_movie.view.*
import ru.vitalysizov.moviedb.R

class HeaderMovieAdapterItem(private val headerTitle: String) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            tv_header_title.text = headerTitle
        }
    }

    override fun getLayout(): Int = R.layout.item_header_movie

}