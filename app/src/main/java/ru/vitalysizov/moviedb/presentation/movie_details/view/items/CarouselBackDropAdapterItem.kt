package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_carousel_backdrop.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.utils.GroupieAdapter

class CarouselBackDropAdapterItem(private val backDropImages: List<Item>) : Item() {

    private val carouselBackdrop = GroupieAdapter()

    override fun createViewHolder(itemView: View): GroupieViewHolder {
        val layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return super.createViewHolder(
            itemView.apply {
                rv_backdrop.layoutManager = layoutManager
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(rv_backdrop)
            })

    }

    override fun getLayout(): Int = R.layout.item_carousel_backdrop

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rv_backdrop.adapter = carouselBackdrop
        carouselBackdrop.addAll(backDropImages)
    }

}