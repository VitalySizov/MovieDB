package ru.vitalysizov.moviedb.data.remote.network.web

import io.reactivex.Single

interface MovieDbWebService {

    fun getAccountUrlAvatar(accountName: String) : Single<String>

}
