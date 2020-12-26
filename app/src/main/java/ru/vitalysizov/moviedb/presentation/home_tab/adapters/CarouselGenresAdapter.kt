package ru.vitalysizov.moviedb.presentation.home_tab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.vitalysizov.moviedb.databinding.ItemCarouselGenresBinding
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.models.GenresCategory
import ru.vitalysizov.moviedb.presentation.home_tab.adapters.viewholders.CarouselGenresViewHolder
import ru.vitalysizov.moviedb.utils.ScrollStateHolder

class CarouselGenresAdapter(
    private val scrollStateHolder: ScrollStateHolder
) : ListAdapter<GenresCategory, CarouselGenresViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<GenresCategory>() {
        override fun areItemsTheSame(oldItem: GenresCategory, newItem: GenresCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GenresCategory, newItem: GenresCategory): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselGenresViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselGenresBinding.inflate(inflater, parent, false)
        val vh = CarouselGenresViewHolder(binding, binding.rvCarouselGenres, scrollStateHolder)

        vh.onCreated()
        return vh
    }

    override fun onBindViewHolder(holder: CarouselGenresViewHolder, position: Int) {
        val currentCarouselGenres = getItem(position)
        val itemBinding = holder.binding as ItemCarouselGenresBinding
        itemBinding.genresCategory = currentCarouselGenres
        itemBinding.executePendingBindings()

        holder.onBound(currentCarouselGenres.genres)
    }

    override fun onViewRecycled(holder: CarouselGenresViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }
}