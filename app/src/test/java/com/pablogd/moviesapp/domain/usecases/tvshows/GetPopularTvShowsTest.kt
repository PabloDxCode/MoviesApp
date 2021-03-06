package com.pablogd.moviesapp.domain.usecases.tvshows

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.moviesapp.common.mockedTvShow
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.TvShowsRepository
import com.pablogd.domain.usescases.tvshows.GetPopularTvShows
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class GetPopularTvShowsTest {

    @Mock
    private lateinit var tvShowsRepository: TvShowsRepository

    private lateinit var getPopularTvShows: GetPopularTvShows

    @Before
    fun setUp() {
        getPopularTvShows = GetPopularTvShows(tvShowsRepository)
    }

    @Test
    fun `invoke get popular tv shows from tv shows repository with success result`() {
        runBlocking {
            val tvShows = Result.Success(listOf(mockedTvShow.copy(id = 1)))
            whenever(tvShowsRepository.getPopularTvShows(1)).thenReturn(tvShows)

            val result = getPopularTvShows.invoke(1)
            assertEquals(tvShows, result)
        }
    }

    @Test
    fun `invoke get popular tv shows from tv shows repository with error result`() {
        runBlocking {
            val error = Result.Error(Exception())
            whenever(tvShowsRepository.getPopularTvShows(1)).thenReturn(error)

            val result = getPopularTvShows.invoke(1)
            assertEquals(error, result)
        }
    }

}