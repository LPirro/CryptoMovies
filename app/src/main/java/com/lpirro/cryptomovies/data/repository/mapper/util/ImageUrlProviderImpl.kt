package com.lpirro.cryptomovies.data.repository.mapper.util

private const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500"

class ImageUrlProviderImpl : ImageUrlProvider {
    override fun provideFullUrl(path: String?): String {
        return "$IMAGES_BASE_URL$path"
    }
}
