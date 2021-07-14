package com.pablogd.domain.usescases.tvshows

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.common.mockedTvShow
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.TvShowsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class GetTopRatedTvShowsTest {

    @Mock
    private lateinit var tvShowsRepository: TvShowsRepository

    private lateinit var getTopRatedTvShows: GetTopRatedTvShows

    @Before
    fun setUp() {
        getTopRatedTvShows = GetTopRatedTvShows(tvShowsRepository)
    }

    @Test
    fun `invoke get top rated tv shows from tv shows repository with success result`() {
        runBlocking {
            val tvShows = Result.Success(listOf(mockedTvShow.copy(id = 1)))
            whenever(tvShowsRepository.getTopRatedTvShows(1)).thenReturn(tvShows)

            val result = getTopRatedTvShows.invoke(1)
            assertEquals(tvShows, result)
        }
    }

    @Test
    fun `invoke get top rated tv shows from tv shows repository with error result`() {
        runBlocking {
            val error = Result.Error(Exception())
            whenever(tvShowsRepository.getTopRatedTvShows(1)).thenReturn(error)

            val result = getTopRatedTvShows.invoke(1)
            assertEquals(error, result)
        }
    }

}