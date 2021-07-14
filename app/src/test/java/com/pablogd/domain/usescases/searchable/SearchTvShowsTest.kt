package com.pablogd.domain.usescases.searchable

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
class SearchTvShowsTest {

    @Mock
    private lateinit var tvShowsRepository: TvShowsRepository

    private lateinit var searchTvShows: SearchTvShows

    @Before
    fun setUp() {
        searchTvShows = SearchTvShows(tvShowsRepository)
    }

    @Test
    fun `invoke search tv shows from search tv shows repository with success response`() {
        runBlocking {
            val tvShows = Result.Success(listOf(mockedTvShow.copy(1)))
            whenever(tvShowsRepository.searchTvShows("loki")).thenReturn(tvShows)
            val result = searchTvShows.invoke("loki")
            assertEquals(tvShows, result)
        }
    }

    @Test
    fun `invoke search tv shows from search tv shows repository with error response`() {
        runBlocking {
            val tvShows = Result.Error(Exception())
            whenever(tvShowsRepository.searchTvShows("loki")).thenReturn(tvShows)
            val result = searchTvShows.invoke("loki")
            assertEquals(tvShows, result)
        }
    }
}