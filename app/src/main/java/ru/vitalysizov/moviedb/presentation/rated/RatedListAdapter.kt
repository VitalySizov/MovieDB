package ru.vitalysizov.moviedb.presentation.rated

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vitalysizov.moviedb.presentation.rated.content.RatedScreenContent
import ru.vitalysizov.moviedb.presentation.rated.content.RatedTab

class RatedListAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {

    private lateinit var ratedScreenContent: RatedScreenContent

    override fun getItemCount(): Int = RatedTab.values().size

    override fun createFragment(position: Int): Fragment {
        val args = RatedListFragmentArgs(
            this.ratedScreenContent,
            RatedTab.getTabByPosition(position)
        )
        val fragment = RatedListFragment()
        fragment.arguments = args.toBundle()
        return fragment
    }

    fun setRatedContent(ratedScreenContent: RatedScreenContent) {
        this.ratedScreenContent = ratedScreenContent
    }
}