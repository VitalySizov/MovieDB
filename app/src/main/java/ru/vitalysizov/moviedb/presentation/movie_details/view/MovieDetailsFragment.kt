package ru.vitalysizov.moviedb.presentation.movie_details.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentMovieDetailsBinding
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.items.MovieDetailsSection
import ru.vitalysizov.moviedb.presentation.movie_details.viewmodel.MovieDetailsViewModel
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    private val movieDetailsSection: MovieDetailsSection by lazy {
        MovieDetailsSection(this, movieDetailsViewModel)
    }

    private val args: MovieDetailsFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.fragment_movie_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DataBindingUtil.bind<FragmentMovieDetailsBinding>(view) ?: return
        initRecyclerView(binding)

        movieDetailsViewModel.loadMovieDetails(args.movieId)
    }

    private fun initRecyclerView(binding: FragmentMovieDetailsBinding) {
        val linearLayoutManager = LinearLayoutManager(context)
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            add(movieDetailsSection)
        }
        binding.rvMovieDetails.layoutManager = linearLayoutManager
        binding.rvMovieDetails.adapter = groupAdapter
    }
}