package ru.vitalysizov.moviedb.data.repo.web

import android.net.Uri
import io.reactivex.Single
import org.jsoup.Jsoup
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.data.remote.network.web.MovieDbWebService
import javax.inject.Inject

class MovieDbWebRepository @Inject constructor() : MovieDbWebService {

    companion object {
        const val USER_PAGE_URL = "${BuildConfig.BASE_WEB_URL}/u/"
        const val AVATAR_CSS_QUERY = "img[class=avatar]"
        const val SRC_ATTR_KEY = "src"
    }

    override fun getAccountUrlAvatar(accountName: String): Single<String> {
        return Single.fromCallable {
            val webUrl = Uri.parse(USER_PAGE_URL)
                .buildUpon()
                .appendPath(accountName)
                .build()
                .toString()

            val url = Jsoup.connect(webUrl)
                .get()
                .select(AVATAR_CSS_QUERY)
                .first()
                .attr(SRC_ATTR_KEY)

            return@fromCallable "${BuildConfig.BASE_WEB_URL}$url"
        }
    }
}