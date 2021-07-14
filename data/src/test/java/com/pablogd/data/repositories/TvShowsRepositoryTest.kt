package com.pablogd.data.repositories

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.data.common.mockedTvShow
import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.TvShowsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.lang.NullPointerException

@RunWith(MockitoJUnitRunner.Silent::class)
class TvShowsRepositoryTest {

    @Mock
    private lateinit var tvShowDataSource: TvShowDataSource

    @Mock
    private lateinit var localTvShowDataSource: LocalTvShowDataSource

    private lateinit var tvShowsRepository: TvShowsRepository

    private lateinit var tvShows: List<TvShow>

    @Before
    fun setUp() {
        tvShows = listOf(mockedTvShow.copy(1))
        tvShowsRepository = TvShowsRepositoryImpl(tvShowDataSource, localTvShowDataSource)
    }

    @Test
    fun `getTvShows get tv shows from remote datasource then save it into local datasource and return it`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(0)
            whenever(tvShowDataSource.getPopularTvShows(0)).thenReturn(tvShows)
            whenever(localTvShowDataSource.getTvShows()).thenReturn(tvShows)

            val result = tvShowsRepository.getTvShows(0)
            assertEquals(Result.Success(tvShows), result)
        }
    }

    @Test
    fun `getTvShows return tv shows saved previously into local datasource`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(1)
            whenever(localTvShowDataSource.getTvShows()).thenReturn(tvShows)

            val result = tvShowsRepository.getTvShows(1)
            assertEquals(Result.Success(tvShows), result)
        }
    }

    @Test
    fun `getTvShows fail when response is null`() {
        runBlocking {
            whenever(tvShowDataSource.getPopularTvShows(1)).thenReturn(null)

            val result = tvShowsRepository.getTvShows(1)
            org.junit.Assert.assertEquals(
                NullPointerException().message,
                (result as Result.Error).exception.message
            )
        }
    }

    @Test
    fun `getPopularTvShows return tv shows categorized by popular from remote datasource`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(1)
            whenever(tvShowDataSource.getPopularTvShows(1)).thenReturn(tvShows)
            whenever(localTvShowDataSource.getTvShows()).thenReturn(tvShows)

            val result = tvShowsRepository.getPopularTvShows(1)
            assertEquals(Result.Success(tvShows), result)
        }
    }

    @Test
    fun `getPopularTvShows fail when response is null and page number is 1`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(1)
            whenever(tvShowDataSource.getPopularTvShows(1)).thenReturn(null)

            val result = tvShowsRepository.getPopularTvShows(1)
            assertEquals(
                Exception(ServiceErrorEnum.NEW_CATEGORY_SELECTED.name).message,
                (result as Result.Error).exception.message
            )
        }
    }

    @Test
    fun `getPopularTvShows fail when response is null and page number is greater than 1`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(10)
            whenever(tvShowDataSource.getPopularTvShows(10)).thenReturn(null)

            val result = tvShowsRepository.getPopularTvShows(10)
            assertEquals(
                NullPointerException().cause,
                (result as Result.Error).exception.cause
            )
        }
    }

    @Test
    fun `getTopRatedTvShows return tv shows categorized by top rated from remote datasource`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(3)
            whenever(tvShowDataSource.getTopRatedTvShows(3)).thenReturn(tvShows)
            whenever(localTvShowDataSource.getTvShows()).thenReturn(tvShows)

            val result = tvShowsRepository.getTopRatedTvShows(3)
            assertEquals(Result.Success(tvShows), result)
        }
    }

    @Test
    fun `getTopRatedTvShows fail when response is null and page number is 1`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(1)
            whenever(tvShowDataSource.getTopRatedTvShows(1)).thenReturn(null)

            val result = tvShowsRepository.getTopRatedTvShows(1)
            assertEquals(
                Exception(ServiceErrorEnum.NEW_CATEGORY_SELECTED.name).message,
                (result as Result.Error).exception.message
            )
        }
    }

    @Test
    fun `getTopRatedTvShows fail when response is null and page number is greater than 1`() {
        runBlocking {
            whenever(localTvShowDataSource.size()).thenReturn(10)
            whenever(tvShowDataSource.getTopRatedTvShows(10)).thenReturn(null)

            val result = tvShowsRepository.getTopRatedTvShows(10)
            assertEquals(
                NullPointerException().cause,
                (result as Result.Error).exception.cause
            )
        }
    }

    @Test
    fun `searchTvShows return specific tv shows from remote datasource`() {
        runBlocking {
            whenever(tvShowDataSource.searchTvShows("Loki")).thenReturn(tvShows)

            val result = tvShowsRepository.searchTvShows("Loki")
            assertEquals(Result.Success(tvShows), result)
        }
    }

    @Test
    fun `searchTvShows fail when response is null`() {
        runBlocking {
            whenever(tvShowDataSource.searchTvShows("Loki")).thenReturn(null)

            val result = tvShowsRepository.searchTvShows("Loki")
            assertEquals(Exception().cause, (result as Result.Error).exception.cause)
        }
    }

}