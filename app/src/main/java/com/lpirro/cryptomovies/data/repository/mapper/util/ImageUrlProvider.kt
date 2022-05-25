package com.lpirro.cryptomovies.data.repository.mapper.util

interface ImageUrlProvider {
    fun provideFullUrl(path: String?): String
}
