package com.pablogd.domain.usescases.searchable

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
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class SearchMoviesTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var searchMovies: SearchMovies

    @Before
    fun setUp() {
        searchMovies = SearchMovies(moviesRepository)
    }

    @Test
    fun `invoke search movies from search movies repository with success response`() {
        runBlocking {
            val movies = Result.Success(listOf(mockedMovie.copy(1)))
            whenever(moviesRepository.searchMovies("ironman")).thenReturn(movies)
            val result = searchMovies.invoke("ironman")
            assertEquals(movies, result)
        }
    }

    @Test
    fun `invoke search movies from search movies repository with error response`() {
        runBlocking {
            val movies = Result.Error(Exception())
            whenever(moviesRepository.searchMovies("ironman")).thenReturn(movies)
            val result = searchMovies.invoke("ironman")
            assertEquals(movies, result)
        }
    }
}