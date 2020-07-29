package ru.vitalysizov.moviedb.presentation.search_result.adapters

import androidx.lifecycle.LifecycleOwner
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.companies.CompanyItem
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchCompanyResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val searchMoviesResultViewModel: SearchResultViewModel,
    private val itemClickListener: (item: CompanyItem) -> Unit
) : BaseSearchResultAdapter() {

    override fun onBindViewHolder(holder: BaseSearchResultViewHolder, position: Int) {
        when (holder) {
            is BaseSearchResultViewHolder.SearchCompanyViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as CompanyItem
                companyItem = currentItem
                root.setOnClickListener { itemClickListener.invoke(currentItem) }
                lifecycleOwner = this@SearchCompanyResultAdapter.lifecycleOwner
            }
            is BaseSearchResultViewHolder.SearchHeaderViewViewHolder -> holder.binding.executeAfter {
                header = R.string.header_found_company
                lifecycleOwner = this@SearchCompanyResultAdapter.lifecycleOwner
            }

            is BaseSearchResultViewHolder.SearchFooterViewViewHolder -> holder.binding.executeAfter {
                count = searchMoviesResultViewModel.searchResultContent.value?.companies?.totalResults
                lifecycleOwner = this@SearchCompanyResultAdapter.lifecycleOwner
            }
        }
    }
}