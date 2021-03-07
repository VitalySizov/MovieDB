package ru.vitalysizov.moviedb.presentation.authentication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.Const
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.databinding.FragmentAuthenticationBinding
import ru.vitalysizov.moviedb.presentation.activity.SystemViewModel
import ru.vitalysizov.moviedb.presentation.authentication.viewmodel.AuthenticationViewModel
import ru.vitalysizov.moviedb.presentation.base.view.BaseFragment
import javax.inject.Inject

class AuthenticationFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentAuthenticationBinding

    private val systemViewModel: SystemViewModel by activityViewModels()

    private val authenticationViewModel: AuthenticationViewModel by viewModels(
        ownerProducer = { requireParentFragment() },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authentication, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = authenticationViewModel
        return binding.root
    }

    //TODO remove SuppressLint
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationViewModel.authUrl.observe(viewLifecycleOwner, { url ->
            binding.webView.loadUrl(url)
        })


        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val uri = request?.url
                when {
                    uri?.host == BuildConfig.HOST_AUTH_CALLBACK && uri.scheme == BuildConfig.AUTH_SCHEME -> {
                        val requestToken = uri.getQueryParameter(Const.ALLOWED_AUTH_REQUEST_TOKEN)
                        if (!requestToken.isNullOrEmpty()) {
                            systemViewModel.setAuthRequestToken(requestToken)
                            findNavController().popBackStack()
                        } else {
                            //TODO error
                        }
                    }
                    uri?.host != BuildConfig.BASE_WEB_URL -> {
                        return false
                    }
                }
                return false
            }
        }
    }
}