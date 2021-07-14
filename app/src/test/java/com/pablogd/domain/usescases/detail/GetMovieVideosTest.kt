package com.pablogd.domain.usescases.detail

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.common.mockedVideos
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.DetailRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class GetMovieVideosTest {

    @Mock
    private lateinit var detailRepository: DetailRepository

    private lateinit var getMovieVideos: GetMovieVideos

    @Before
    fun setUp() {
        getMovieVideos = GetMovieVideos(detailRepository)
    }

    @Test
    fun `invoke get movie videos from get detail repository with success result`() {
        runBlocking {
            val videos = Result.Success(listOf(mockedVideos.copy("1")))
            whenever(detailRepository.getMovieVideos(1)).thenReturn(videos)

            val result = getMovieVideos.invoke(1)
            assertEquals(videos, result)
        }
    }

    @Test
    fun `invoke get movie videos from get detail repository with error result`() {
        runBlocking {
            val error = Result.Error(Exception())
            whenever(detailRepository.getMovieVideos(1)).thenReturn(error)

            val result = getMovieVideos.invoke(1)
            assertEquals(error, result)
        }
    }

}