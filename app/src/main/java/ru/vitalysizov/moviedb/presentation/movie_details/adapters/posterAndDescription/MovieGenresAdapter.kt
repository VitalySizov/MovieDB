package ru.vitalysizov.moviedb.presentation.movie_details.adapters.posterAndDescription

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemGenreDetailsMovieBinding
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class MovieGenresAdapter : ListAdapter<GenreItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<GenreItem>() {
        override fun areItemsTheSame(oldItem: GenreItem, newItem: GenreItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreItem, newItem: GenreItem): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreDetailsMovieBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = getItem(position)
        val itemBinding = holder.binding as ItemGenreDetailsMovieBinding
        itemBinding.genreItem = currentItem
        itemBinding.executePendingBindings()
    }
}