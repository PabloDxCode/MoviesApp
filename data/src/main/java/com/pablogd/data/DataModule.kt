package com.pablogd.data

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.datasource.LocalMovieDataSource
import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.data.datasource.impl.LocalMovieDataSourceImpl
import com.pablogd.data.datasource.impl.LocalTvShowDataSourceImpl
import com.pablogd.data.datasource.impl.MovieDataSourceImpl
import com.pablogd.data.datasource.impl.TvShowDataSourceImpl
import com.pablogd.data.repositories.MoviesRepositoryImpl
import com.pablogd.data.repositories.TvShowsRepositoryImpl
import com.pablogd.data.server.MoviesApiConfig
import com.pablogd.domain.repositories.MoviesRepository
import com.pablogd.domain.repositories.TvShowsRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single(named("apiKey")) { "85a7b9637aaeff8978059b6e28822b43" }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }

    single { MoviesDB.build(get(), "movies") }
    single { MoviesApiConfig(get(named("baseUrl"))) }

    factory<MovieDataSource> { MovieDataSourceImpl(get(named("apiKey")), get()) }
    factory<TvShowDataSource> { TvShowDataSourceImpl(get(named("apiKey")), get()) }

    factory<LocalMovieDataSource> { LocalMovieDataSourceImpl(get()) }
    factory<LocalTvShowDataSource> { LocalTvShowDataSourceImpl(get()) }

    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
    factory<TvShowsRepository> { TvShowsRepositoryImpl(get(), get()) }
}