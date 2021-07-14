package com.pablogd.moviesapp.domain.usecases.detail

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.moviesapp.common.mockedVideos
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.DetailRepository
import com.pablogd.domain.usescases.detail.GetTvShowVideos
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class GetTvShowVideosTest {

    @Mock
    private lateinit var detailRepository: DetailRepository

    private lateinit var getTvShowVideos: GetTvShowVideos

    @Before
    fun setUp() {
        getTvShowVideos = GetTvShowVideos(detailRepository)
    }

    @Test
    fun `invoke get tv show videos from get detail repository with success result`() {
        runBlocking {
            val videos = Result.Success(listOf(mockedVideos.copy("1")))
            whenever(detailRepository.getTvShowVideos(1)).thenReturn(videos)

            val result = getTvShowVideos.invoke(1)
            assertEquals(videos, result)
        }
    }

    @Test
    fun `invoke get tv show videos from get detail repository with error result`() {
        runBlocking {
            val error = Result.Error(Exception())
            whenever(detailRepository.getTvShowVideos(1)).thenReturn(error)

            val result = getTvShowVideos.invoke(1)
            assertEquals(error, result)
        }
    }

}