package com.pablogd.moviesapp.domain.usecases.movies

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.moviesapp.common.mockedMovie
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.MoviesRepository
import com.pablogd.domain.usescases.movies.GetMovies
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getMovies: GetMovies

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
    }

    @Test
    fun `invoke get movies from movies repository with success result`() {
        runBlocking {
            val movies = Result.Success(listOf(mockedMovie.copy(id = 1)))
            whenever(moviesRepository.getMovies(1)).thenReturn(movies)

            val result = getMovies.invoke(1)
            assertEquals(movies, result)
        }
    }

    @Test
    fun `invoke get movies from movies repository with error result`() {
        runBlocking {
            val error = Result.Error(Exception())
            whenever(moviesRepository.getMovies(1)).thenReturn(error)

            val result = getMovies.invoke(1)
            assertEquals(error, result)
        }
    }

}