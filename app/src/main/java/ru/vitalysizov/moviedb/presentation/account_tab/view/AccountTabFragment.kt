package ru.vitalysizov.moviedb.presentation.account_tab.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentAccountTabBinding
import ru.vitalysizov.moviedb.presentation.account_tab.viewmodel.AccountTabViewModel
import ru.vitalysizov.moviedb.presentation.activity.SystemViewModel
import ru.vitalysizov.moviedb.presentation.authentication.view.AuthenticationFragmentArgs
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import ru.vitalysizov.moviedb.utils.CustomTabHelper
import javax.inject.Inject

class AccountTabFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentAccountTabBinding
    private val customTabHelper = CustomTabHelper()

    private val systemViewModel: SystemViewModel by activityViewModels()

    private val accountTabViewModel: AccountTabViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_tab, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = accountTabViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAuth.setOnClickListener {
            accountTabViewModel.onLoginClicked()
        }

        binding.btnLogout.setOnClickListener {
            accountTabViewModel.onLogoutClicked()
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_accountTabFragment_to_settingsFragment)
        }

        systemViewModel.authRequestToken.observe(viewLifecycleOwner, { authRequestToken ->
            authRequestToken.getContentIfNotHandled()?.let { requestToken ->
                accountTabViewModel.onCreateSessionAndGetAccount(requestToken)
            }
        })

        accountTabViewModel.authUrl.observe(viewLifecycleOwner, { authUrl ->
            authUrl.getContentIfNotHandled()?.let {
                val customTabsBuilder = CustomTabsIntent.Builder()
                val customTabColorSchemeParams = CustomTabColorSchemeParams.Builder()
                customTabColorSchemeParams.apply {
                    setToolbarColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimary
                        )
                    )
                }
                customTabsBuilder.apply {
                    setColorSchemeParams(
                        CustomTabsIntent.COLOR_SCHEME_DARK,
                        customTabColorSchemeParams.build()
                    )
                    setShowTitle(true)
                    setStartAnimations(
                        requireContext(),
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    setExitAnimations(
                        requireContext(),
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                }

                val customTabsIntent = customTabsBuilder.build()
                val availablePackageName = customTabHelper.getPackageNameToUse(requireContext(), it)

                if (availablePackageName == null) {
                    val args = AuthenticationFragmentArgs(it)
                    findNavController().navigate(
                        R.id.action_accountTabFragment_to_authenticationFragment,
                        args.toBundle()
                    )
                } else {
                    customTabsIntent.intent.apply {
                        setPackage(availablePackageName)
                        flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    customTabsIntent.launchUrl(requireContext(), Uri.parse(it))
                }
            }
        })
    }

}