package ru.vitalysizov.moviedb.presentation.search_tab.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.ItemSearchMovieBinding
import ru.vitalysizov.moviedb.databinding.ItemSearchPersonBinding
import ru.vitalysizov.moviedb.databinding.ItemSearchToResultsBinding
import ru.vitalysizov.moviedb.databinding.ItemSearchTvShowBinding
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchTabAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val actionResultsDetails: () -> Unit,
    private val actionMovieItemClick: (item: MovieItem) -> Unit,
    private val actionTvShowItemClick: (item: TvShowItem) -> Unit,
    private val actionPersonItemClick: (item: PersonItem) -> Unit
) : RecyclerView.Adapter<SearchTabViewHolder>() {

    private val differ = AsyncListDiffer<Any>(this, DiffCallback)

    var items: List<Any> = emptyList()
        set(value) {
            field = value
            differ.submitList(generateItemsWithFooterToResults(value))
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTabViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_search_movie -> SearchTabViewHolder.SearchMovieViewHolder(
                ItemSearchMovieBinding.inflate(inflater, parent, false)
            )
            R.layout.item_search_tv_show -> SearchTabViewHolder.SearchTvShowViewHolder(
                ItemSearchTvShowBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_person -> SearchTabViewHolder.SearchPersonViewHolder(
                ItemSearchPersonBinding.inflate(inflater, parent, false)
            )
            R.layout.item_search_to_results -> SearchTabViewHolder.SearchToResultsViewHolder(
                ItemSearchToResultsBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is MovieItem -> R.layout.item_search_movie
            is TvShowItem -> R.layout.item_search_tv_show
            is PersonItem -> R.layout.item_search_person
            is ToResults -> R.layout.item_search_to_results
            else -> throw IllegalStateException("Unknown view type at position: $position")
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun generateItemsWithFooterToResults(items: List<Any>): List<Any> {
        if (items.isEmpty()) {
            return emptyList()
        }
        val currentItems: MutableList<Any> = items.toMutableList()
        currentItems.add(ToResults)
        return currentItems
    }

    override fun onBindViewHolder(holder: SearchTabViewHolder, position: Int) {
        when (holder) {
            is SearchTabViewHolder.SearchMovieViewHolder -> holder.binding.executeAfter {
                val currentItem = differ.currentList[position] as MovieItem
                movieItem = currentItem
                root.setOnClickListener { actionMovieItemClick.invoke(currentItem) }
                lifecycleOwner = this@SearchTabAdapter.lifecycleOwner
            }

            is SearchTabViewHolder.SearchTvShowViewHolder -> holder.binding.executeAfter {
                val currentItem = differ.currentList[position] as TvShowItem
                tvShowItem = currentItem
                root.setOnClickListener { actionTvShowItemClick.invoke(currentItem) }
                lifecycleOwner = this@SearchTabAdapter.lifecycleOwner
            }

            is SearchTabViewHolder.SearchPersonViewHolder -> holder.binding.executeAfter {
                val currentItem = differ.currentList[position] as PersonItem
                personItem = currentItem
                root.setOnClickListener { actionPersonItemClick.invoke(currentItem) }
                lifecycleOwner = this@SearchTabAdapter.lifecycleOwner
            }

            is SearchTabViewHolder.SearchToResultsViewHolder -> holder.binding.executeAfter {
                btnToResults.setOnClickListener { actionResultsDetails.invoke() }
                lifecycleOwner = this@SearchTabAdapter.lifecycleOwner
            }
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is MovieItem && newItem is MovieItem -> oldItem.id == newItem.id
            oldItem is TvShowItem && newItem is TvShowItem -> oldItem.id == newItem.id
            oldItem is PersonItem && newItem is PersonItem -> oldItem.id == newItem.id
            oldItem is ToResults && newItem is ToResults -> oldItem == newItem
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is MovieItem && newItem is MovieItem -> oldItem == newItem
            oldItem is TvShowItem && newItem is TvShowItem -> oldItem == newItem
            oldItem is PersonItem && newItem is PersonItem -> oldItem == newItem
            oldItem is ToResults && newItem is ToResults -> oldItem == newItem
            else -> true
        }
    }

}

object ToResults

sealed class SearchTabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class SearchMovieViewHolder(val binding: ItemSearchMovieBinding) :
        SearchTabViewHolder(binding.root)

    class SearchTvShowViewHolder(val binding: ItemSearchTvShowBinding) :
        SearchTabViewHolder(binding.root)

    class SearchPersonViewHolder(val binding: ItemSearchPersonBinding) :
        SearchTabViewHolder(binding.root)

    class SearchToResultsViewHolder(val binding: ItemSearchToResultsBinding) :
        SearchTabViewHolder(binding.root)
}

