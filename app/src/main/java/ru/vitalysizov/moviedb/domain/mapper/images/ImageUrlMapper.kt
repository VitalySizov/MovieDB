package ru.vitalysizov.moviedb.domain.mapper.images

import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.data.local.prefs.AccountPreferences
import ru.vitalysizov.moviedb.data.local.prefs.ConfigurationPreferences
import ru.vitalysizov.moviedb.domain.mapper.Mapper
import javax.inject.Inject

class ImageUrlMapper @Inject constructor(
    private val configurationPreferences: ConfigurationPreferences,
    private val accountPreferences: AccountPreferences
) : Mapper<UrlPathAndType, String> {

    override fun map(from: UrlPathAndType): String {
        if (from.path.isEmpty()) {
            return ""
        }

        val secureBaseUrl = configurationPreferences.configuration?.images?.secureBaseUrl.orEmpty()
        if (secureBaseUrl.isEmpty()) {
            return "${BuildConfig.BASE_IMG_URL}${BuildConfig.DEFAULT_IMG_QUALITY}$from"
        }

        val imageQuality = configurationPreferences.imageQuality
        val size: String? = when (from.imageType) {
            ImageTypes.IMAGE_BACKDROP -> {
                imageQuality?.backdropSizes?.get(accountPreferences.imageQuality)
            }
            ImageTypes.IMAGE_LOGO -> {
                imageQuality?.logoSizes?.get(accountPreferences.imageQuality)
            }
            ImageTypes.IMAGE_POSTER -> {
                imageQuality?.posterSizes?.get(accountPreferences.imageQuality)
            }
            ImageTypes.IMAGE_PROFILE -> {
                imageQuality?.profileSizes?.get(accountPreferences.imageQuality)
            }
            ImageTypes.IMAGE_STILL -> {
                imageQuality?.stillSizes?.get(accountPreferences.imageQuality)
            }
        }
        return "${secureBaseUrl}$size${from.path}"
    }
}