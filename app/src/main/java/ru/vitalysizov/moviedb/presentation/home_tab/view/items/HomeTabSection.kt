package ru.vitalysizov.moviedb.presentation.home_tab.view.items

import androidx.lifecycle.LifecycleOwner
import com.xwray.groupie.Section
import androidx.lifecycle.observe
import com.xwray.groupie.Group
import ru.vitalysizov.moviedb.presentation.home_tab.viewmodel.HomeTabViewModel

class HomeTabSection(
    lifecycleOwner: LifecycleOwner,
    homeTabViewModel: HomeTabViewModel
) : Section() {

    init {
        homeTabViewModel.loadItems.observe(lifecycleOwner) { items ->
            update(items)
        }
    }
}