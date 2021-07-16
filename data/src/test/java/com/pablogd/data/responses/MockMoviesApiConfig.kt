package com.pablogd.data.responses

import com.pablogd.data.BuildConfig
import com.pablogd.data.server.MoviesApi
import com.pablogd.data.server.MoviesApiConfig
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MockMoviesApiConfig(private val baseUrl: HttpUrl): MoviesApiConfig {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder()
            .addInterceptor(this)
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()
    }

    override fun service(): MoviesApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run { create(MoviesApi::class.java) }

}