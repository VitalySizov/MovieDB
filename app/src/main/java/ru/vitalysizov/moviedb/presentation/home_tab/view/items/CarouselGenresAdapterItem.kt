package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_carousel_genres.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class CarouselGenresAdapterItem(private val genresItems: List<Item>) : Item() {

    private val genresAdapterItem = GroupieAdapter()

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        val linearLayoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        return super.createViewHolder(itemView.apply {
            rv_carousel_genres.layoutManager = linearLayoutManager
        })
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rv_carousel_genres.adapter = genresAdapterItem
        genresAdapterItem.clear()
        genresAdapterItem.addAll(genresItems)
    }

    override fun getLayout(): Int = R.layout.item_carousel_genres
}