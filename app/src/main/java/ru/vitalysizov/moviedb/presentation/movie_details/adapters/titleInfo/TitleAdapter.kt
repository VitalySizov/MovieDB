package ru.vitalysizov.moviedb.presentation.movie_details.adapters.titleInfo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemTitleMovieDetailsBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.presentation.base.adapter.viewHolder.BaseViewHolder

class TitleAdapter : ListAdapter<MovieDetailsItem, BaseViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<MovieDetailsItem>() {
        override fun areItemsTheSame(
            oldItem: MovieDetailsItem,
            newItem: MovieDetailsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieDetailsItem,
            newItem: MovieDetailsItem
        ): Boolean {
            return oldItem.originalTitle == oldItem.originalTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTitleMovieDetailsBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        val titleBinding = holder.binding as ItemTitleMovieDetailsBinding
        titleBinding.movieDetails = item
    }

}