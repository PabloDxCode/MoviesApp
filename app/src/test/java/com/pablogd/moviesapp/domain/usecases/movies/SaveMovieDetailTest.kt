package com.pablogd.moviesapp.domain.usecases.movies

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.moviesapp.common.mockedMovie
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.DetailRepository
import com.pablogd.domain.usescases.movies.SaveMovieDetail
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveMovieDetailTest {

    @Mock
    private lateinit var detailRepository: DetailRepository

    private lateinit var saveMovieDetail: SaveMovieDetail

    @Before
    fun setUp() {
        saveMovieDetail = SaveMovieDetail(detailRepository)
    }

    @Test
    fun `invoke save movie detail from detail repository with success result`() {
        runBlocking {
            val movie = mockedMovie.copy(1)
            val response = Result.Success(true)
            whenever(detailRepository.saveDetail(movie)).thenReturn(response)
            val result = saveMovieDetail.invoke(movie)
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `invoke save movie detail from detail repository with error result`() {
        runBlocking {
            val movie = mockedMovie.copy(1)
            val response = Result.Success(false)
            whenever(detailRepository.saveDetail(movie)).thenReturn(response)
            val result = saveMovieDetail.invoke(movie)
            Assert.assertEquals(response, result)
        }
    }
}