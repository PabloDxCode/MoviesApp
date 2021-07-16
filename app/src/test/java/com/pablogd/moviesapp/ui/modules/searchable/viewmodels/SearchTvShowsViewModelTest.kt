package com.pablogd.moviesapp.ui.modules.searchable.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.domain.Result
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.usescases.searchable.SearchTvShows
import com.pablogd.domain.usescases.tvshows.SaveTvShowDetail
import com.pablogd.moviesapp.common.mockedTvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class SearchTvShowsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<SearchTvShowsViewModel.UiModel>

    @Mock
    private lateinit var searchTvShows: SearchTvShows

    @Mock
    private lateinit var saveTvShowDetail: SaveTvShowDetail

    private lateinit var viewModel: SearchTvShowsViewModel

    private lateinit var tvShows: List<TvShow>

    @Before
    fun setUp() {
        tvShows = listOf(mockedTvShow.copy(1), mockedTvShow.copy(2))
        viewModel = SearchTvShowsViewModel(searchTvShows, saveTvShowDetail, Dispatchers.Unconfined)
    }

    @Test
    fun `getSearch return list of tv shows by query from remote service`() {
        runBlocking {
            whenever(searchTvShows.invoke("Loki")).thenReturn(Result.Success(tvShows))
            viewModel.model.observeForever(observer)

            viewModel.getSearch("Loki")
            verify(searchTvShows).invoke("Loki")
            verify(observer).onChanged(SearchTvShowsViewModel.UiModel.Content(tvShows))
        }
    }

    @Test
    fun `getSearch fail and return error result`() {
        runBlocking {
            whenever(searchTvShows.invoke("WandaVision")).thenReturn(Result.Error(Exception()))
            viewModel.model.observeForever(observer)

            viewModel.getSearch("WandaVision")
            verify(observer).onChanged(
                SearchTvShowsViewModel.UiModel.Error(
                    Exception().message ?: ""
                )
            )
        }
    }

    @Test
    fun `saveTvShowDetail will save detail and navigate to detail view`() {
        runBlocking {
            val tvShow = tvShows[0]
            whenever(saveTvShowDetail.invoke(tvShow)).thenReturn(Result.Success(true))
            viewModel.model.observeForever(observer)

            viewModel.saveItemClicked(tvShow)
            verify(saveTvShowDetail).invoke(tvShow)
            verify(observer).onChanged(SearchTvShowsViewModel.UiModel.Navigation)
        }
    }
}