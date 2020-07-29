package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemGenreBinding
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class GenreAdapter : ListAdapter<GenreItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<GenreItem>() {
        override fun areItemsTheSame(oldItem: GenreItem, newItem: GenreItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreItem, newItem: GenreItem): Boolean {
            return oldItem.name == oldItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(inflate, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentGenre = getItem(position)
        val itemBinding = holder.binding as ItemGenreBinding
        itemBinding.genre = currentGenre
        itemBinding.executePendingBindings()
    }
}