package ru.vitalysizov.moviedb.data.local.prefs

import android.content.SharedPreferences
import ru.vitalysizov.moviedb.domain.mapper.images.ImageQualityTypes
import ru.vitalysizov.moviedb.model.domain.account.AccountDetailsItem
import ru.vitalysizov.moviedb.utils.json
import ru.vitalysizov.moviedb.utils.stringNullable

class AccountPreferences(prefs: SharedPreferences) {

    var sessionId by prefs.stringNullable()
    var account: AccountDetailsItem? by prefs.json(null)
    var imageQuality: ImageQualityTypes = ImageQualityTypes.MEDIUM_QUALITY

    fun clearAccountPreferences() {
        sessionId = null
        account = null
        imageQuality = ImageQualityTypes.MEDIUM_QUALITY
    }

}