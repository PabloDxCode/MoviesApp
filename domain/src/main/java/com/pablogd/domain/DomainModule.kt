package com.pablogd.domain

import com.pablogd.domain.usescases.detail.GetDetail
import com.pablogd.domain.usescases.movies.GetMovies
import com.pablogd.domain.usescases.movies.GetPopularMovies
import com.pablogd.domain.usescases.movies.GetTopRatedMovies
import com.pablogd.domain.usescases.movies.SaveMovieDetail
import com.pablogd.domain.usescases.tvshows.GetPopularTvShows
import com.pablogd.domain.usescases.tvshows.GetTopRatedTvShows
import com.pablogd.domain.usescases.tvshows.GetTvShows
import com.pablogd.domain.usescases.tvshows.SaveTvShowDetail
import org.koin.dsl.module

val domainModule = module {
    factory { GetMovies(get()) }
    factory { GetPopularMovies(get()) }
    factory { GetTopRatedMovies(get()) }
    factory { SaveMovieDetail(get()) }

    factory { GetTvShows(get()) }
    factory { GetPopularTvShows(get()) }
    factory { GetTopRatedTvShows(get()) }
    factory { SaveTvShowDetail(get()) }

    factory { GetDetail(get()) }
}