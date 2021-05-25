package ru.vitalysizov.moviedb.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.vitalysizov.moviedb.Const
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.presentation.base.view.BaseActivity
import javax.inject.Inject

class AppActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val systemViewModel: SystemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val requestToken = intent?.data?.getQueryParameter(Const.ALLOWED_AUTH_REQUEST_TOKEN)
        if (!requestToken.isNullOrEmpty()) {
            systemViewModel.setAuthRequestToken(requestToken)
        }
    }
}
