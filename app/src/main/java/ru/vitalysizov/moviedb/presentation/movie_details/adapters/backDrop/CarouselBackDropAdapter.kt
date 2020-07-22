package ru.vitalysizov.moviedb.presentation.movie_details.adapters.backDrop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import ru.vitalysizov.moviedb.databinding.ItemCarouselBackdropBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieImages
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class CarouselBackDropAdapter : ListAdapter<MovieImages, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<MovieImages>() {
        override fun areItemsTheSame(
            oldItem: MovieImages,
            newItem: MovieImages
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieImages,
            newItem: MovieImages
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselBackdropBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentBackDrop = getItem(position)
        val itemBinding = holder.binding as ItemCarouselBackdropBinding
        itemBinding.backDrops = currentBackDrop.backdrops

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(itemBinding.rvBackdrops)

        itemBinding.executePendingBindings()
    }

}