package ru.vitalysizov.moviedb.presentation.search_result.adapters

import androidx.lifecycle.LifecycleOwner
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.collection.CollectionItem
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchCollectionResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val searchMoviesResultViewModel: SearchResultViewModel,
    private val itemClickListener: (item: CollectionItem) -> Unit
) : BaseSearchResultAdapter() {

    override fun onBindViewHolder(holder: BaseSearchResultViewHolder, position: Int) {
        when (holder) {
            is BaseSearchResultViewHolder.SearchCollectionViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as CollectionItem
                collectionItem = currentItem
                root.setOnClickListener { itemClickListener.invoke(currentItem) }
                lifecycleOwner = this@SearchCollectionResultAdapter.lifecycleOwner
            }
            is BaseSearchResultViewHolder.SearchHeaderViewViewHolder -> holder.binding.executeAfter {
                header = R.string.header_found_collection
                lifecycleOwner = this@SearchCollectionResultAdapter.lifecycleOwner
            }

            is BaseSearchResultViewHolder.SearchFooterViewViewHolder -> holder.binding.executeAfter {
                count =
                    searchMoviesResultViewModel.searchResultContent.value?.collections?.totalResults
                lifecycleOwner = this@SearchCollectionResultAdapter.lifecycleOwner
            }
        }
    }
}