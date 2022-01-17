package ru.vitalysizov.moviedb.data.remote.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class IncludeImageLanguageInterceptor @Inject constructor() : Interceptor {

    private companion object {
        const val INCLUDE_IMAGE_LANGUAGE = "include_image_language"
        const val LANGUAGE_PARAMS = "null"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val urlBuilder = url.newBuilder()

        // TODO: rework
        if (request.url.encodedPath.contains("/images", true)) {
            urlBuilder.addQueryParameter(INCLUDE_IMAGE_LANGUAGE, LANGUAGE_PARAMS)
        }

        val newUrl = urlBuilder.build()
        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}