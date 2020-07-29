package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.item_carousel_movies.view.*
import ru.vitalysizov.moviedb.databinding.ItemCarouselMoviesBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.models.MoviesCategory
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.viewholders.CarouselMoviesViewHolder
import ru.vitalysizov.moviedb.utils.ScrollStateHolder


class CarouselMoviesAdapter(
    private val scrollStateHolder: ScrollStateHolder,
    private val listener: ItemClickListener<MovieItem>
) : ListAdapter<MoviesCategory, CarouselMoviesViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<MoviesCategory>() {
        override fun areItemsTheSame(oldItem: MoviesCategory, newItem: MoviesCategory): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MoviesCategory, newItem: MoviesCategory): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselMoviesBinding.inflate(inflater, parent, false)
        val vh = CarouselMoviesViewHolder(binding, binding.root.rvCarouselMovies, scrollStateHolder)

        vh.onCreated()
        return vh
    }

    override fun onBindViewHolder(holder: CarouselMoviesViewHolder, position: Int) {
        val currentCarouselMovies = getItem(position)
        val itemBinding = holder.binding as ItemCarouselMoviesBinding
        itemBinding.listener = listener
        itemBinding.moviesCategory = currentCarouselMovies
        itemBinding.executePendingBindings()

        holder.onBound(currentCarouselMovies.movies)
    }

    override fun onViewRecycled(holder: CarouselMoviesViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }
}