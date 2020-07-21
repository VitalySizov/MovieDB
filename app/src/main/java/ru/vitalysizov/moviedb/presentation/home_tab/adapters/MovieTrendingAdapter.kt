package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemMovieTrendingBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class MovieTrendingAdapter(val listener: ItemClickListener<MovieItem>) :
    ListAdapter<MovieItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieTrendingBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = getItem(position)
        val itemBinding = holder.binding as ItemMovieTrendingBinding
        itemBinding.root.setOnClickListener { listener.onClickListener(currentItem) }
        itemBinding.movie = currentItem
        itemBinding.executePendingBindings()
    }
}