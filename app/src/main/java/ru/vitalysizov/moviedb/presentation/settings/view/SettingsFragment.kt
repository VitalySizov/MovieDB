package ru.vitalysizov.moviedb.presentation.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentSettingsBinding
import ru.vitalysizov.moviedb.presentation.activity.SystemViewModel
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.presentation.settings.viewmodel.SettingsViewModel
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentSettingsBinding

    private val viewModel: SettingsViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    private val systemViewModel: SystemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemViewModel.onHideBottomNavigation()
        binding.includeAppBar.toolbar.title = resources.getString(R.string.settings_title)
        binding.includeAppBar.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}