package com.pablogd.domain.usescases.movies

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.common.mockedMovie
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTopRatedMoviesTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getTopRatedMovies: GetTopRatedMovies

    @Before
    fun setUp() {
        getTopRatedMovies = GetTopRatedMovies(moviesRepository)
    }

    @Test
    fun `invoke get top rated movies from movies repository with success result`() {
        runBlocking {
            val movies = Result.Success(listOf(mockedMovie.copy(id = 1)))
            whenever(moviesRepository.getTopRatedMovies(1)).thenReturn(movies)

            val result = getTopRatedMovies.invoke(1)
            assertEquals(movies, result)
        }
    }

    @Test
    fun `invoke get top rated movies from movies repository with error result`() {
        runBlocking {
            val error = Result.Error(Exception())
            whenever(moviesRepository.getTopRatedMovies(1)).thenReturn(error)

            val result = getTopRatedMovies.invoke(1)
            assertEquals(error, result)
        }
    }

}