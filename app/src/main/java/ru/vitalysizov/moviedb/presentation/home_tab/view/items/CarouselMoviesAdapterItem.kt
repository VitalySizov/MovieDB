package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_carousel_movies.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class CarouselMoviesAdapterItem(private val movieItems: ArrayList<Item>) : Item() {

    private val moviesAdapter = GroupieAdapter()

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        val linearLayoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        return super.createViewHolder(itemView.apply {
            rv_carousel.layoutManager = linearLayoutManager
        })
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rv_carousel.adapter = moviesAdapter
        moviesAdapter.clear()
        moviesAdapter.addAll(movieItems)
    }

    fun updateList(items: List<Item>) {
        movieItems.clear()
        movieItems.addAll(items)
    }

    override fun getLayout(): Int = R.layout.item_carousel_movies
}