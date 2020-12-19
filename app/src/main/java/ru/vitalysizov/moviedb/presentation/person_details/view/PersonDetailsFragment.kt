package ru.vitalysizov.moviedb.presentation.person_details.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentPersonDetailsBinding
import ru.vitalysizov.moviedb.model.domain.person.PersonCastMovieItem
import ru.vitalysizov.moviedb.model.domain.person.PersonCastTvItem
import ru.vitalysizov.moviedb.presentation.base.ItemClickListener
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.movie_details.view.MovieDetailsFragmentArgs
import ru.vitalysizov.moviedb.presentation.person_details.adapters.cast.PersonCastAndCrewAdapter
import ru.vitalysizov.moviedb.presentation.person_details.viewmodel.PersonDetailsViewModel
import ru.vitalysizov.moviedb.presentation.search_result.viewmodel.SearchResultFragmentArgs
import ru.vitalysizov.moviedb.utils.dismissKeyboard
import javax.inject.Inject

class PersonDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val personDetailsViewModel: PersonDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPersonDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_person_details, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = personDetailsViewModel

        binding.ibFacebook.setOnClickListener { openPersonFacebook() }
        binding.ibInstagram.setOnClickListener { openPersonInstagram() }
        binding.ibTwitter.setOnClickListener { openPersonTwitter() }

        val filmographyAdapter =
            PersonCastAndCrewAdapter(this, this::onMovieClickListener, this::onTvShowClickListener)

        personDetailsViewModel.personContent.observe(viewLifecycleOwner) { items ->
            filmographyAdapter.submitList(items.personCombinedCredits.cast)
            binding.rvFilmography.adapter = filmographyAdapter
        }

        personDetailsViewModel.movieDetailsClicked.observe(viewLifecycleOwner) { item ->
            item.getContentIfNotHandled()?.let {
                navigateToMovieDetails(it)
            }
        }

        personDetailsViewModel.tvShowDetailsClick.observe(viewLifecycleOwner, Observer { item ->
            item.getContentIfNotHandled()?.let {
                //TODO: navigate to tvShow details
            }
        })

        return binding.root
    }

    private fun openPersonFacebook() {
        val facebookUri = getString(
            R.string.facebook_url_with_id,
            personDetailsViewModel.personContent.value?.personExternalIds?.faceBookId
        )
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(facebookUri)))
    }

    private fun openPersonInstagram() {
        val instagramUri = getString(
            R.string.instagram_url_with_id,
            personDetailsViewModel.personContent.value?.personExternalIds?.instagramId
        )
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(instagramUri)))
    }

    private fun openPersonTwitter() {
        val twitterUri = getString(
            R.string.twitter_url_with_id,
            personDetailsViewModel.personContent.value?.personExternalIds?.twitterId
        )
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(twitterUri)))
    }

    private fun onMovieClickListener(item: PersonCastMovieItem) {
        personDetailsViewModel.setMovieDetailsClick(item.id)
    }

    private fun onTvShowClickListener(item: PersonCastTvItem) {
        personDetailsViewModel.setTvShowDetailsClick(item.id)
    }

    //TODO: it's dirty navigation need refactoring
    private fun navigateToMovieDetails(movieId: Int) {
        val args = MovieDetailsFragmentArgs(movieId)
        try {
            findNavController().navigate(
                R.id.homeMovieDetailsFragment,
                args.toBundle()
            )
        } catch (e: IllegalArgumentException) {
            try {
                findNavController().navigate(
                    R.id.searchMovieDetailsFragment,
                    args.toBundle()
                )
            } finally {

            }
        }
    }
}