package ru.vitalysizov.moviedb.presentation.search_result.adapters

import androidx.lifecycle.LifecycleOwner
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.keywords.KeywordItem
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchKeywordResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val searchMoviesResultViewModel: SearchResultViewModel,
    private val itemClickListener: (item: KeywordItem) -> Unit
) : BaseSearchResultAdapter() {

    override fun onBindViewHolder(holder: BaseSearchResultViewHolder, position: Int) {
        when (holder) {
            is BaseSearchResultViewHolder.SearchKeyWordViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as KeywordItem
                keywordItem = currentItem
                root.setOnClickListener { itemClickListener.invoke(currentItem) }
                lifecycleOwner = this@SearchKeywordResultAdapter.lifecycleOwner
            }
            is BaseSearchResultViewHolder.SearchHeaderViewViewHolder -> holder.binding.executeAfter {
                header = R.string.header_found_keywords
                lifecycleOwner = this@SearchKeywordResultAdapter.lifecycleOwner
            }

            is BaseSearchResultViewHolder.SearchFooterViewViewHolder -> holder.binding.executeAfter {
                count = searchMoviesResultViewModel.searchResultContent.value?.keywords?.totalResults
                lifecycleOwner = this@SearchKeywordResultAdapter.lifecycleOwner
            }
        }
    }
}