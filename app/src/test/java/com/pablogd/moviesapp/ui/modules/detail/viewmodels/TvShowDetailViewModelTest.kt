package com.pablogd.moviesapp.ui.modules.detail.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.domain.Result
import com.pablogd.domain.models.Video
import com.pablogd.domain.usescases.detail.GetTvShowVideos
import com.pablogd.moviesapp.common.mockedVideos
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
class TvShowDetailViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TvShowDetailViewModel.UiModel>

    @Mock
    private lateinit var getTvShowVideos: GetTvShowVideos

    private lateinit var viewModel: TvShowDetailViewModel

    private lateinit var videos: List<Video>

    @Before
    fun setUp() {
        videos = listOf(mockedVideos.copy("1"), mockedVideos.copy("2"))
        viewModel = TvShowDetailViewModel(getTvShowVideos, Dispatchers.Unconfined)
    }

    @Test
    fun `getVideos return videos from specific tv show id`() {
        runBlocking {
            whenever(getTvShowVideos.invoke(12)).thenReturn(Result.Success(videos))
            viewModel.model.observeForever(observer)

            viewModel.getVideos(12)
            verify(getTvShowVideos).invoke(12)
            verify(observer).onChanged(TvShowDetailViewModel.UiModel.Content(videos))
        }
    }

    @Test
    fun `getVideos fail when it try to get tv show videos`() {
        runBlocking {
            whenever(getTvShowVideos.invoke(6)).thenReturn(Result.Error(Exception()))
            viewModel.model.observeForever(observer)

            viewModel.getVideos(6)
            verify(getTvShowVideos).invoke(6)
            verify(observer).onChanged(
                TvShowDetailViewModel.UiModel.Error(
                    Exception().message ?: ""
                )
            )
        }
    }

}