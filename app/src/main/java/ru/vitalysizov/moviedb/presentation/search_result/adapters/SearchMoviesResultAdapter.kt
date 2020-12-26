package ru.vitalysizov.moviedb.presentation.search_result.adapters


import androidx.lifecycle.LifecycleOwner
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.movies.MovieItem
import ru.vitalysizov.moviedb.presentation.search_result.view.SearchResultViewModel
import ru.vitalysizov.moviedb.utils.executeAfter

class SearchMoviesResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val searchMoviesResultViewModel: SearchResultViewModel,
    private val itemClickListener: (item: MovieItem) -> Unit
) : BaseSearchResultAdapter() {

    override fun onBindViewHolder(holder: BaseSearchResultViewHolder, position: Int) {
        when (holder) {
            is BaseSearchResultViewHolder.SearchMovieViewViewHolder -> holder.binding.executeAfter {
                val currentItem = getItem(position) as MovieItem
                movieItem = currentItem
                root.setOnClickListener {
                    itemClickListener.invoke(
                        currentItem
                    )
                }
                lifecycleOwner = this@SearchMoviesResultAdapter.lifecycleOwner
            }
            is BaseSearchResultViewHolder.SearchHeaderViewViewHolder -> holder.binding.executeAfter {
                header = R.string.header_found_films
                lifecycleOwner = this@SearchMoviesResultAdapter.lifecycleOwner
            }

            is BaseSearchResultViewHolder.SearchFooterViewViewHolder -> holder.binding.executeAfter {
                count = searchMoviesResultViewModel.searchResultContent.value?.movies?.totalResults
                lifecycleOwner = this@SearchMoviesResultAdapter.lifecycleOwner
            }
        }
    }
}