package ru.vitalysizov.moviedb.data.remote.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.vitalysizov.moviedb.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddDefaultParametersInterceptor @Inject constructor() : Interceptor {

    private companion object {
        const val DEFAULT_LANGUAGE = "language"
        const val LANGUAGE = "ru-RU"
        const val DEFAULT_API_KEY = "api_key"
        const val API_KEY = BuildConfig.API_KEY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        val urlBuilder = url.newBuilder()
            .addQueryParameter(DEFAULT_API_KEY, API_KEY)
            .addQueryParameter(DEFAULT_LANGUAGE, LANGUAGE)

        val newUrl = urlBuilder.build()
        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

}