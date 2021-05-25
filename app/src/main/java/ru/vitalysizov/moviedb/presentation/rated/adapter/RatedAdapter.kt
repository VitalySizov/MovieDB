package ru.vitalysizov.moviedb.presentation.rated.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.ItemSearchMovieBinding
import ru.vitalysizov.moviedb.databinding.ItemSearchTvShowBinding
import ru.vitalysizov.moviedb.databinding.ItemTvEpisodeBinding
import ru.vitalysizov.moviedb.model.domain.movies.RatedMovieItem
import ru.vitalysizov.moviedb.model.domain.tvEpisodes.RatedTvEpisodeItem
import ru.vitalysizov.moviedb.model.domain.tvShows.RatedTvShowItem
import ru.vitalysizov.moviedb.utils.executeAfter

class RatedAdapter : ListAdapter<Any, RatedAdapter.RatedViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is RatedMovieItem && newItem is RatedMovieItem -> oldItem.movieItem == newItem.movieItem
                oldItem is RatedTvShowItem && newItem is RatedTvShowItem -> oldItem.tvShowItem == newItem.tvShowItem
                oldItem is RatedTvEpisodeItem && newItem is RatedTvEpisodeItem -> oldItem == newItem
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is RatedMovieItem && newItem is RatedMovieItem -> oldItem.movieItem.id == newItem.movieItem.id
                oldItem is RatedTvShowItem && newItem is RatedTvShowItem -> oldItem.tvShowItem.id == newItem.tvShowItem.id
                oldItem is RatedTvEpisodeItem && newItem is RatedTvEpisodeItem -> oldItem.id == newItem.id
                else -> false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_search_movie -> {
                RatedViewHolder.RatedMovieViewHolder(
                    ItemSearchMovieBinding.inflate(inflater, parent, false)
                )
            }
            R.layout.item_search_tv_show -> {
                RatedViewHolder.RatedTvShowViewHolder(
                    ItemSearchTvShowBinding.inflate(inflater, parent, false)
                )
            }
            R.layout.item_tv_episode -> {
                RatedViewHolder.RatedTvEpisodeViewHolder(
                    ItemTvEpisodeBinding.inflate(inflater, parent, false)
                )
            }
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RatedViewHolder, position: Int) {
        when (holder) {
            is RatedViewHolder.RatedMovieViewHolder -> {
                val item = getItem(position) as RatedMovieItem
                holder.binding.executeAfter {
                    movieItem = item.movieItem
                }
            }
            is RatedViewHolder.RatedTvShowViewHolder -> {
                val item = getItem(position) as RatedTvShowItem
                holder.binding.executeAfter {
                    tvShowItem = item.tvShowItem
                }
            }
            is RatedViewHolder.RatedTvEpisodeViewHolder -> {
                val item = getItem(position) as RatedTvEpisodeItem
                holder.binding.executeAfter {
                    ratedTvEpisode = item
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RatedMovieItem -> {
                R.layout.item_search_movie
            }
            is RatedTvShowItem -> {
                R.layout.item_search_tv_show
            }
            is RatedTvEpisodeItem -> {
                R.layout.item_tv_episode
            }
            else -> throw IllegalStateException("Unknown view type at position: $position")
        }
    }

    sealed class RatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class RatedMovieViewHolder(val binding: ItemSearchMovieBinding) :
            RatedViewHolder(binding.root)

        class RatedTvShowViewHolder(val binding: ItemSearchTvShowBinding) :
            RatedViewHolder(binding.root)

        class RatedTvEpisodeViewHolder(val binding: ItemTvEpisodeBinding) :
            RatedViewHolder(binding.root)

    }

}