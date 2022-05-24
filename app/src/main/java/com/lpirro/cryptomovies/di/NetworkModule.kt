package com.lpirro.cryptomovies.di

import android.content.Context
import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.network.interceptors.ApiKeyInterceptor
import com.lpirro.cryptomovies.data.network.interceptors.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRetrofit(@ApplicationContext appContext: Context): CryptoMovieService = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttp(appContext))
        .build()
        .create(CryptoMovieService::class.java)

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideOkHttp(appContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(NetworkConnectionInterceptor(appContext))
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}
