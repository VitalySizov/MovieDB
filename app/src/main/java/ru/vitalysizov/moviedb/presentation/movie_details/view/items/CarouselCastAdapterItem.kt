package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_carousel_cast.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class CarouselCastAdapterItem(private val castItems: List<Item>) : Item() {

    private val groupieAdapter = GroupieAdapter()

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        val layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return super.createViewHolder(
            itemView.apply {
                rvCast.layoutManager = layoutManager
            }
        )
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rvCast.adapter = groupieAdapter
        groupieAdapter.addAll(castItems)
    }

    override fun getLayout(): Int = R.layout.item_carousel_cast
}