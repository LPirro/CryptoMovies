package com.lpirro.cryptomovies.di

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.network.interceptors.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org/"
private const val API_VERSION = "3"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun getBaseUrl() = "$BASE_URL$API_VERSION/"

    @Singleton
    @Provides
    fun provideRetrofit(): CryptoMovieService = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttp())
        .build()
        .create(CryptoMovieService::class.java)

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}
