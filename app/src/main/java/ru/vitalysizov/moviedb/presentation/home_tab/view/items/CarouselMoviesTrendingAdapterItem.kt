package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_carousel_trending.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class CarouselMoviesTrendingAdapterItem(private val moviesItems: ArrayList<Item>) : Item() {

    private val trendingMoviesAdapter = GroupieAdapter()

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        val layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return super.createViewHolder(itemView.apply {
            rv_carousel_trending.layoutManager = layoutManager
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(rv_carousel_trending)
        })
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rv_carousel_trending.adapter = trendingMoviesAdapter
        trendingMoviesAdapter.clear()
        trendingMoviesAdapter.addAll(moviesItems)
    }

    fun updateList(items: List<Item>) {
        moviesItems.clear()
        moviesItems.addAll(items)
    }

    override fun getLayout(): Int = R.layout.item_carousel_trending
}