package ru.vitalysizov.moviedb.domain.useCase.configuration

import androidx.collection.arrayMapOf
import io.reactivex.Single
import ru.vitalysizov.moviedb.domain.mapper.images.ImageQuality
import ru.vitalysizov.moviedb.domain.mapper.images.ImageQualityTypes
import ru.vitalysizov.moviedb.domain.useCase.base.SingleWithParamsUseCase
import ru.vitalysizov.moviedb.model.domain.configuration.ConfigurationItem
import javax.inject.Inject

class FormatImageQualityUseCase @Inject constructor() :
    SingleWithParamsUseCase<ConfigurationItem, ImageQuality>() {

    private companion object {
        const val ORIGINAL_IMAGE_QUALITY = "original"
        const val HALF = 2

        private fun formatList(sizes: List<String>): Map<ImageQualityTypes, String> {
            val format = arrayMapOf<ImageQualityTypes, String>()
            val items = sizes.filter { it != ORIGINAL_IMAGE_QUALITY }
            val middle = items.lastIndex / HALF

            items.forEachIndexed { index, sizeValue ->
                if (index == 0) {
                    format[ImageQualityTypes.LOW_QUALITY] = sizeValue
                }
                if (index == middle) {
                    format[ImageQualityTypes.MEDIUM_QUALITY] = sizeValue
                }

                if (index == items.lastIndex) {
                    format[ImageQualityTypes.HIGH_QUALITY] = sizeValue
                }
            }

            //TODO: handle the situation when it came less than three sizes
            if (format.size != 3) {
                format[ImageQualityTypes.LOW_QUALITY] = "w200"
                format[ImageQualityTypes.MEDIUM_QUALITY] = "w300"
                format[ImageQualityTypes.HIGH_QUALITY] = "w500"
            }

            return format
        }
    }

    override fun invoke(params: ConfigurationItem): Single<ImageQuality> {
        val backdropSizes = formatList(params.images.backdropSizes)
        val logoSizes = formatList(params.images.logoSizes)
        val posterSizes = formatList(params.images.posterSizes)
        val profileSizes = formatList(params.images.profileSizes)
        val stillSizes = formatList(params.images.stillSizes)

        val imageQuality = ImageQuality(
            backdropSizes = backdropSizes,
            logoSizes = logoSizes,
            posterSizes = posterSizes,
            profileSizes = profileSizes,
            stillSizes = stillSizes
        )
        return Single.just(imageQuality)
    }

}