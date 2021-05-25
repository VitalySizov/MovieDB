package ru.vitalysizov.moviedb.data.repo.impl

import io.reactivex.Single
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.model.domain.configuration.ConfigurationImagesItem
import ru.vitalysizov.moviedb.model.domain.configuration.ConfigurationItem

class ConfigurationRepository(private val apiService: IMovieDbApiService) {

    fun getConfiguration(): Single<ConfigurationItem> {
        return apiService.getConfiguration().map {
            val images = it.images
            ConfigurationItem(
                images = ConfigurationImagesItem(
                    baseUrl = images?.baseUrl.orEmpty(),
                    secureBaseUrl = images?.secureBaseUrl.orEmpty(),
                    backdropSizes = images?.backdropSizes ?: emptyList(),
                    logoSizes = images?.logoSizes ?: emptyList(),
                    posterSizes = images?.posterSizes ?: emptyList(),
                    profileSizes = images?.profileSizes ?: emptyList(),
                    stillSizes = images?.stillSizes ?: emptyList()
                ),
                changeKeys = it.changeKeys ?: emptyList()
            )
        }
    }
}