package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import ru.vitalysizov.moviedb.databinding.ItemCarouselMoviesTrendingBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.models.MoviesCategory
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.viewholders.CarouselMoviesViewHolder
import ru.vitalysizov.moviedb.utils.ScrollStateHolder

class CarouselMoviesTrendingAdapter(
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
        val binding = ItemCarouselMoviesTrendingBinding.inflate(inflater, parent, false)
        val vh = CarouselMoviesViewHolder(binding, binding.rvCarouselMoviesTrending, scrollStateHolder)

        vh.onCreated()
        return vh
    }

    override fun onBindViewHolder(holder: CarouselMoviesViewHolder, position: Int) {
        val current = getItem(position)
        val itemBinding = holder.binding as ItemCarouselMoviesTrendingBinding
        itemBinding.moviesCategory = current
        itemBinding.listener = listener

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(itemBinding.rvCarouselMoviesTrending)

        itemBinding.executePendingBindings()
        holder.onBound(current.movies)
    }

}