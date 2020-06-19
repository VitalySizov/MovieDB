package ru.vitalysizov.moviedb.presentation.movie_details.view.items

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.xwray.groupie.Section
import ru.vitalysizov.moviedb.presentation.movie_details.viewmodel.MovieDetailsViewModel

class MovieDetailsSection(
    lifecycleOwner: LifecycleOwner,
    movieDetailsViewModel: MovieDetailsViewModel
) : Section() {

    init {
        movieDetailsViewModel.loadItems.observe(lifecycleOwner) { items ->
            update(items)
        }
    }
}