package ru.vitalysizov.moviedb.presentation.search_result.adapters

import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.item_search_tv_show.view.*
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.tvShows.TvShowItem
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchTvShowResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val searchMoviesResultViewModel: SearchResultViewModel,
    private val itemClickListener: (item: TvShowItem) -> Unit
) : BaseSearchResultAdapter() {

    override fun onBindViewHolder(viewHolder: BaseSearchResultViewHolder, position: Int) {
        when (viewHolder) {
            is BaseSearchResultViewHolder.SearchTvShowViewViewHolder -> viewHolder.binding.executeAfter {
                val currentItem = getItem(position) as TvShowItem
                tvShowItem = currentItem
                root.linearLayoutSearchShow.setOnClickListener {
                    itemClickListener.invoke(
                        currentItem
                    )
                }
                lifecycleOwner = this@SearchTvShowResultAdapter.lifecycleOwner
            }
            is BaseSearchResultViewHolder.SearchHeaderViewViewHolder -> viewHolder.binding.executeAfter {
                header = R.string.header_found_tv_shows
                lifecycleOwner = this@SearchTvShowResultAdapter.lifecycleOwner
            }

            is BaseSearchResultViewHolder.SearchFooterViewViewHolder -> viewHolder.binding.executeAfter {
                count = searchMoviesResultViewModel.searchResultContent.value?.tvShows?.totalResults
                lifecycleOwner = this@SearchTvShowResultAdapter.lifecycleOwner
            }
        }
    }
}