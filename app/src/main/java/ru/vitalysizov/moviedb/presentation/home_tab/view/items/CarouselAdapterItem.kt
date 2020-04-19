package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_carouse_movies.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class CarouselAdapterItem(private val movieItems: List<Item>) : Item() {

    private val moviesAdapter = GroupieAdapter()
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        linearLayoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        return super.createViewHolder(itemView.apply {
            rv_carousel.layoutManager = linearLayoutManager
            rv_carousel.setHasFixedSize(true)
        })
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rv_carousel.adapter = moviesAdapter
        moviesAdapter.clear()
        moviesAdapter.addAll(movieItems)
    }

    override fun getLayout(): Int = R.layout.item_carouse_movies
}