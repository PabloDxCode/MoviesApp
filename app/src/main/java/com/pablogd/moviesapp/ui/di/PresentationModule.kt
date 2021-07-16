package com.pablogd.moviesapp.ui.di

import com.pablogd.moviesapp.ui.modules.detail.viewmodels.DetailViewModel
import com.pablogd.moviesapp.ui.modules.detail.viewmodels.MovieDetailViewModel
import com.pablogd.moviesapp.ui.modules.detail.viewmodels.TvShowDetailViewModel
import com.pablogd.moviesapp.ui.modules.home.viewmodels.MoviesViewModel
import com.pablogd.moviesapp.ui.modules.home.viewmodels.TvShowsViewModel
import com.pablogd.moviesapp.ui.modules.searchable.viewmodels.SearchMoviesViewModel
import com.pablogd.moviesapp.ui.modules.searchable.viewmodels.SearchTvShowsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    single(named("iuDispatcher")) { Dispatchers.Main as CoroutineDispatcher }

    viewModel { MoviesViewModel(get(), get(), get(), get(), get(named("iuDispatcher"))) }
    viewModel { TvShowsViewModel(get(), get(), get(), get(), get(named("iuDispatcher"))) }

    viewModel { DetailViewModel(get(), get(named("iuDispatcher"))) }
    viewModel { MovieDetailViewModel(get(), get(named("iuDispatcher"))) }
    viewModel { TvShowDetailViewModel(get(), get(named("iuDispatcher"))) }

    viewModel { SearchMoviesViewModel(get(), get(), get(named("iuDispatcher"))) }
    viewModel { SearchTvShowsViewModel(get(), get(), get(named("iuDispatcher"))) }
}