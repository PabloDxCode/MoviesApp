package com.pablogd.data.server

import com.pablogd.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MoviesApiConfigImpl(private val baseUrl: String): MoviesApiConfig {

    companion object {

        private const val READ_TIME_OUT: Long = 60
        private const val CONNECTION_TIME_OUT: Long = 60

    }

    private val okHttpClient = HttpLoggingInterceptor().run {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder().addInterceptor(this).readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS).build()
    }

    override fun service(): MoviesApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .run { create(MoviesApi::class.java) }

}