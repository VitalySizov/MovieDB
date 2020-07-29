package ru.vitalysizov.moviedb.presentation.search_result.adapters

import androidx.lifecycle.LifecycleOwner
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.persons.PersonItem
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchPersonResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val searchMoviesResultViewModel: SearchResultViewModel,
    private val itemClickListener: (item: PersonItem) -> Unit
) : BaseSearchResultAdapter() {

    override fun onBindViewHolder(holder: BaseSearchResultViewHolder, position: Int) {
        when (holder) {
            is BaseSearchResultViewHolder.SearchPersonViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as PersonItem
                personItem = currentItem
                root.setOnClickListener { itemClickListener.invoke(currentItem) }
                lifecycleOwner = this@SearchPersonResultAdapter.lifecycleOwner
            }
            is BaseSearchResultViewHolder.SearchHeaderViewViewHolder -> holder.binding.executeAfter {
                header = R.string.header_found_persons
                lifecycleOwner = this@SearchPersonResultAdapter.lifecycleOwner
            }

            is BaseSearchResultViewHolder.SearchFooterViewViewHolder -> holder.binding.executeAfter {
                count = searchMoviesResultViewModel.searchResultContent.value?.persons?.totalResults
                lifecycleOwner = this@SearchPersonResultAdapter.lifecycleOwner
            }
        }
    }
}