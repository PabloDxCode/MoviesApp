package com.pablogd.moviesapp.ui.modules.home.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.usescases.tvshows.GetPopularTvShows
import com.pablogd.domain.usescases.tvshows.GetTopRatedTvShows
import com.pablogd.domain.usescases.tvshows.GetTvShows
import com.pablogd.domain.usescases.tvshows.SaveTvShowDetail
import com.pablogd.moviesapp.common.mockedTvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<TvShowsViewModel.UiModel>

    @Mock
    private lateinit var getTvShows: GetTvShows

    @Mock
    private lateinit var getPopularTvShows: GetPopularTvShows

    @Mock
    private lateinit var getTopRatedTvShows: GetTopRatedTvShows

    @Mock
    private lateinit var saveTvShowDetail: SaveTvShowDetail

    private lateinit var tvShowsViewModel: TvShowsViewModel

    private lateinit var tvShows: List<TvShow>

    @Before
    fun setUp() {
        tvShows = listOf(mockedTvShow.copy(1), mockedTvShow.copy(2), mockedTvShow.copy(3))
        tvShowsViewModel = TvShowsViewModel(
            getTvShows,
            getPopularTvShows,
            getTopRatedTvShows,
            saveTvShowDetail,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `getTvShows return initial data from database when app is started`() {
        runBlocking {
            whenever(getTvShows.invoke(1)).thenReturn(Result.Success(tvShows))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getTvShows()
            verify(getTvShows).invoke(1)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Content(tvShows))
        }
    }

    @Test
    fun `getTvShows fail and return error result`() {
        runBlocking {
            whenever(getTvShows.invoke(1)).thenReturn(Result.Error(Exception()))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getTvShows()
            verify(getTvShows).invoke(1)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Error(Exception().message ?: ""))
        }
    }

    @Test
    fun `getPopularTvShows return popular tv shows from db`() {
        runBlocking {
            whenever(getPopularTvShows.invoke(0)).thenReturn(Result.Success(tvShows))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getPopularTvShows(0)
            verify(getPopularTvShows).invoke(0)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Content(tvShows))
        }
    }

    @Test
    fun `getPopularTvShows fail and return category error`() {
        runBlocking {
            val messageError = ServiceErrorEnum.NEW_CATEGORY_SELECTED.name
            whenever(getPopularTvShows.invoke(0)).thenReturn(Result.Error(Exception(messageError)))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getPopularTvShows(0)
            verify(getPopularTvShows).invoke(0)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Error(messageError))
        }
    }

    @Test
    fun `getPopularTvShows fail and return error result`() {
        runBlocking {
            whenever(getPopularTvShows.invoke(1)).thenReturn(Result.Error(Exception()))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getPopularTvShows(1)
            verify(getPopularTvShows).invoke(1)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Error(Exception().message ?: ""))
        }
    }

    @Test
    fun `getTopRatedTvShows return popular tv shows from db`() {
        runBlocking {
            whenever(getTopRatedTvShows.invoke(0)).thenReturn(Result.Success(tvShows))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getTopRatedTvShows(0)
            verify(getTopRatedTvShows).invoke(0)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Content(tvShows))
        }
    }

    @Test
    fun `getTopRatedTvShows fail and return category error`() {
        runBlocking {
            val messageError = ServiceErrorEnum.NEW_CATEGORY_SELECTED.name
            whenever(getTopRatedTvShows.invoke(0)).thenReturn(Result.Error(Exception(messageError)))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getTopRatedTvShows(0)
            verify(getTopRatedTvShows).invoke(0)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Error(messageError))
        }
    }

    @Test
    fun `getTopRatedTvShows fail and return error result`() {
        runBlocking {
            whenever(getTopRatedTvShows.invoke(1)).thenReturn(Result.Error(Exception()))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.getTopRatedTvShows(1)
            verify(getTopRatedTvShows).invoke(1)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Error(Exception().message ?: ""))
        }
    }

    @Test
    fun `saveTvShowDetail will save detail and navigate to detail view`() {
        runBlocking {
            val tvShow = tvShows[0]
            whenever(saveTvShowDetail.invoke(tvShow)).thenReturn(Result.Success(true))
            tvShowsViewModel.model.observeForever(observer)

            tvShowsViewModel.saveItemClicked(tvShow)
            verify(saveTvShowDetail).invoke(tvShow)
            verify(observer).onChanged(TvShowsViewModel.UiModel.Navigation)
        }
    }

}