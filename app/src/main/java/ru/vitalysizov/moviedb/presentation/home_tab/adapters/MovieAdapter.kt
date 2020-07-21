package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemMovieBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class MovieAdapter(private val itemListener: ItemClickListener<MovieItem>) :
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
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentMovie = getItem(position)
        val itemBinding = holder.binding as ItemMovieBinding
        itemBinding.root.setOnClickListener { itemListener.onClickListener(currentMovie) }
        itemBinding.movie = currentMovie
        itemBinding.executePendingBindings()
    }
}