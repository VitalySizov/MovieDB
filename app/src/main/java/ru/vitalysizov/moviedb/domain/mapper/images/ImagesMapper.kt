package ru.vitalysizov.moviedb.domain.mapper.images

import ru.vitalysizov.moviedb.domain.mapper.Mapper
import ru.vitalysizov.moviedb.model.domain.images.ImageItem
import ru.vitalysizov.moviedb.model.network.responses.images.ImageItemResponse
import javax.inject.Inject

class ImagesMapper @Inject constructor(
    private val imageUrlMapper: ImageUrlMapper
) : Mapper<Pair<List<ImageItemResponse>?, ImageTypes>, List<ImageItem>> {

    override fun map(from: Pair<List<ImageItemResponse>?, ImageTypes>): List<ImageItem> {
        val imagesResponse = from.first
        val imageType = from.second
        if (imagesResponse.isNullOrEmpty()) {
            return emptyList()
        }

        val list = arrayListOf<ImageItem>()
        imagesResponse.forEach {
            list.add(
                ImageItem(
                    aspectRatio = it.aspectRatio ?: 0.0,
                    filePath = imageUrlMapper.map(
                        UrlPathAndType(
                            path = it.filePath.orEmpty(),
                            imageType = imageType
                        )
                    ),
                    height = it.height ?: 0,
                    iso_639_1 = it.iso_639_1.orEmpty(),
                    voteAverage = it.voteAverage ?: 0.0,
                    voteCount = it.voteCount ?: 0,
                    width = it.width ?: 0
                )
            )
        }

        return list
    }
}
