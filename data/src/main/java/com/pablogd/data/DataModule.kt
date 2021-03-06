package com.pablogd.data

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.datasource.*
import com.pablogd.data.datasource.impl.*
import com.pablogd.data.repositories.DetailRepositoryImpl
import com.pablogd.data.repositories.MoviesRepositoryImpl
import com.pablogd.data.repositories.TvShowsRepositoryImpl
import com.pablogd.data.server.MoviesApiConfig
import com.pablogd.data.server.MoviesApiConfigImpl
import com.pablogd.domain.repositories.DetailRepository
import com.pablogd.domain.repositories.MoviesRepository
import com.pablogd.domain.repositories.TvShowsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single(named("apiKey")) { BuildConfig.MOVIES_API_KEY }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }

    single { MoviesDB.build(get(), BuildConfig.MOVIE_DB_NAME) }
    single<MoviesApiConfig> { MoviesApiConfigImpl(get(named("baseUrl"))) }

    factory<MovieDataSource> { MovieDataSourceImpl(get(named("apiKey")), get()) }
    factory<TvShowDataSource> { TvShowDataSourceImpl(get(named("apiKey")), get()) }

    single(named("dataDispatcher")) { Dispatchers.IO }
    factory<LocalMovieDataSource> { LocalMovieDataSourceImpl(get(), get(named("dataDispatcher"))) }
    factory<LocalTvShowDataSource> {
        LocalTvShowDataSourceImpl(
            get(),
            get(named("dataDispatcher"))
        )
    }
    factory<LocalDetailDataSource> {
        LocalDetailDataSourceImpl(
            get(),
            get(named("dataDispatcher"))
        )
    }

    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
    factory<TvShowsRepository> { TvShowsRepositoryImpl(get(), get()) }
    factory<DetailRepository> { DetailRepositoryImpl(get(), get(), get()) }
}