package ru.vitalysizov.moviedb.presentation.search_result.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.*
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.model.domain.keywords.KeywordItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem

abstract class BaseSearchResultAdapter : ListAdapter<Any, BaseSearchResultViewHolder>(Companion) {

    fun generateDataWithHeaderAndFooter(items: List<Any>) {
        val generateItems: MutableList<Any> = ArrayList()
        if (items.isNotEmpty()) {
            generateItems.add(HeaderWithTitle)
            generateItems.addAll(items)
            generateItems.add(FooterWithCount)
            submitList(generateItems)
        } else {
            submitList(items)
        }
    }

    companion object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is MovieItem && newItem is MovieItem -> oldItem.id == newItem.id
                oldItem is TvShowItem && newItem is TvShowItem -> oldItem.id == newItem.id
                oldItem is PersonItem && newItem is PersonItem -> oldItem.id == newItem.id
                oldItem is CompanyItem && newItem is CompanyItem -> oldItem.id == newItem.id
                oldItem is CollectionItem && newItem is CollectionItem -> oldItem.id == newItem.id
                oldItem is KeywordItem && newItem is KeywordItem -> oldItem.id == newItem.id
                oldItem is HeaderWithTitle && newItem is HeaderWithTitle -> oldItem == newItem
                oldItem is FooterWithCount && newItem is FooterWithCount -> oldItem == newItem
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is MovieItem && newItem is MovieItem -> oldItem.title == newItem.title
                oldItem is TvShowItem && newItem is TvShowItem -> oldItem.name == newItem.name
                oldItem is PersonItem && newItem is PersonItem -> oldItem.name == newItem.name
                oldItem is CompanyItem && newItem is CompanyItem -> oldItem.name == newItem.name
                oldItem is CollectionItem && newItem is CollectionItem -> oldItem.name == newItem.name
                oldItem is KeywordItem && newItem is KeywordItem -> oldItem.name == newItem.name
                else -> false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_search_movie -> BaseSearchResultViewHolder.SearchMovieViewViewHolder(
                ItemSearchMovieBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_tv_show -> BaseSearchResultViewHolder.SearchTvShowViewViewHolder(
                ItemSearchTvShowBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_person -> BaseSearchResultViewHolder.SearchPersonViewHolder(
                ItemSearchPersonBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_company -> BaseSearchResultViewHolder.SearchCompanyViewHolder(
                ItemSearchCompanyBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_collection -> BaseSearchResultViewHolder.SearchCollectionViewHolder(
                ItemSearchCollectionBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_keyword -> BaseSearchResultViewHolder.SearchKeyWordViewHolder(
                ItemSearchKeywordBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_results_header -> BaseSearchResultViewHolder.SearchHeaderViewViewHolder(
                ItemSearchResultsHeaderBinding.inflate(inflater, parent, false)
            )

            R.layout.item_search_result_footer -> BaseSearchResultViewHolder.SearchFooterViewViewHolder(
                ItemSearchResultFooterBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MovieItem -> R.layout.item_search_movie
            is TvShowItem -> R.layout.item_search_tv_show
            is PersonItem -> R.layout.item_search_person
            is CompanyItem -> R.layout.item_search_company
            is CollectionItem -> R.layout.item_search_collection
            is KeywordItem -> R.layout.item_search_keyword
            is HeaderWithTitle -> R.layout.item_search_results_header
            is FooterWithCount -> R.layout.item_search_result_footer
            else -> throw IllegalStateException("Unknown view type at position: $position")
        }
    }
}

sealed class BaseSearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class SearchMovieViewViewHolder(val binding: ItemSearchMovieBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchTvShowViewViewHolder(val binding: ItemSearchTvShowBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchPersonViewHolder(val binding: ItemSearchPersonBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchCompanyViewHolder(val binding: ItemSearchCompanyBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchCollectionViewHolder(val binding: ItemSearchCollectionBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchKeyWordViewHolder(val binding: ItemSearchKeywordBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchHeaderViewViewHolder(val binding: ItemSearchResultsHeaderBinding) :
        BaseSearchResultViewHolder(binding.root)

    class SearchFooterViewViewHolder(val binding: ItemSearchResultFooterBinding) :
        BaseSearchResultViewHolder(binding.root)
}