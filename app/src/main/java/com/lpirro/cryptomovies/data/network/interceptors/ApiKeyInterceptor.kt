package com.lpirro.cryptomovies.data.network.interceptors

import com.lpirro.cryptomovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_VALUE = "api_key"

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter(API_KEY_VALUE, BuildConfig.API_KEY)
            .build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
