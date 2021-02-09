package ru.vitalysizov.moviedb.data.local.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.vitalysizov.moviedb.model.domain.account.AccountDetailsItem

class MovieDbPreferences(
    private val prefs: SharedPreferences,
    private val gson: Gson
) {

    companion object {
        const val SESSION_ID = "session_id"
        const val ACCOUNT = "account"
    }

    var sessionId: String?
        get() = prefs.getString(SESSION_ID, null)
        set(value) = prefs.edit().putString(SESSION_ID, value).apply()

    var account: AccountDetailsItem?
        get() {
            val accountJson = prefs.getString(ACCOUNT, null) ?: return null
            return try {
                gson.fromJson(accountJson, AccountDetailsItem::class.java)
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(ACCOUNT, gson.toJson(value)).apply()

}