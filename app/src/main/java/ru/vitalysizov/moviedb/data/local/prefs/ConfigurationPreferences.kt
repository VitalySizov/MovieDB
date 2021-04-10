package ru.vitalysizov.moviedb.data.local.prefs

import android.content.SharedPreferences
import ru.vitalysizov.moviedb.domain.mapper.images.ImageQuality
import ru.vitalysizov.moviedb.model.domain.configuration.ConfigurationItem
import ru.vitalysizov.moviedb.utils.json

class ConfigurationPreferences(prefs: SharedPreferences) {

    var configuration: ConfigurationItem? by prefs.json(null)
    var imageQuality: ImageQuality? by prefs.json(null)
}