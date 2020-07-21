package ru.vitalysizov.moviedb.presentation.home_tab.adapters.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.utils.ScrollStateHolder

class CarouselMoviesViewHolder(
    val binding: ViewDataBinding,
    private val recyclerView: RecyclerView,
    private val scrollStateHolder: ScrollStateHolder
) : RecyclerView.ViewHolder(binding.root), ScrollStateHolder.ScrollStateKeyProvider {

    private var currentItem: List<MovieItem>? = null

    fun onCreated() {
        scrollStateHolder.setupRecyclerView(recyclerView, this)
    }

    fun onBound(item: List<MovieItem>) {
        currentItem = item
        scrollStateHolder.restoreScrollState(recyclerView, this)
    }

    fun onRecycled() {
        scrollStateHolder.saveScrollState(recyclerView, this)
    }

    override fun getScrollStateKey(): String? {
        return currentItem?.get(bindingAdapterPosition)?.id.toString()
    }
}