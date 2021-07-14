package com.pablogd.moviesapp.ui.di

import com.pablogd.moviesapp.ui.modules.detail.viewmodels.DetailViewModel
import com.pablogd.moviesapp.ui.modules.home.viewmodels.MoviesViewModel
import com.pablogd.moviesapp.ui.modules.home.viewmodels.TvShowsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }

    viewModel { MoviesViewModel(get(), get(), get(), get(), get()) }
    viewModel { TvShowsViewModel(get(), get(), get(), get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
}