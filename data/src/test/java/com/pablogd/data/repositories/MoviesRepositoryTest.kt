package com.pablogd.data.repositories

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.data.common.mockedMovie
import com.pablogd.data.datasource.LocalMovieDataSource
import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.Movie
import com.pablogd.domain.repositories.MoviesRepository
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.lang.NullPointerException

@RunWith(MockitoJUnitRunner.Silent::class)
class MoviesRepositoryTest {

    @Mock
    private lateinit var movieDataSource: MovieDataSource

    @Mock
    private lateinit var localMovieDataSource: LocalMovieDataSource

    private lateinit var moviesRepository: MoviesRepository

    private lateinit var movies: List<Movie>

    @Before
    fun setUp() {
        movies = listOf(mockedMovie.copy(1))
        moviesRepository = MoviesRepositoryImpl(movieDataSource, localMovieDataSource)
    }

    @Test
    fun `getMovies get movies from remote datasource then save it into local datasource and return it`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(0)
            whenever(movieDataSource.getPopularMovies(0)).thenReturn(movies)
            whenever(localMovieDataSource.getMovies()).thenReturn(movies)

            val result = moviesRepository.getMovies(0)
            assertEquals(Result.Success(movies), result)
        }
    }

    @Test
    fun `getMovies return movies saved previously into local datasource`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(1)
            whenever(localMovieDataSource.getMovies()).thenReturn(movies)

            val result = moviesRepository.getMovies(1)
            assertEquals(Result.Success(movies), result)
        }
    }

    @Test
    fun `getMovies fail when response is null`() {
        runBlocking {
            whenever(movieDataSource.getPopularMovies(1)).thenReturn(null)

            val result = moviesRepository.getMovies(1)
            Assert.assertEquals(
                NullPointerException().message,
                (result as Result.Error).exception.message
            )
        }
    }

    @Test
    fun `getPopularMovies return movies categorized by popular from remote datasource`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(1)
            whenever(movieDataSource.getPopularMovies(1)).thenReturn(movies)
            whenever(localMovieDataSource.getMovies()).thenReturn(movies)

            val result = moviesRepository.getPopularMovies(1)
            assertEquals(Result.Success(movies), result)
        }
    }

    @Test
    fun `getPopularMovies fail when response is null and page number is 1`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(1)
            whenever(movieDataSource.getPopularMovies(1)).thenReturn(null)

            val result = moviesRepository.getPopularMovies(1)
            assertEquals(
                Exception(ServiceErrorEnum.NEW_CATEGORY_SELECTED.name).message,
                (result as Result.Error).exception.message
            )
        }
    }

    @Test
    fun `getPopularMovies fail when response is null and page number is greater than 1`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(10)
            whenever(movieDataSource.getPopularMovies(10)).thenReturn(null)

            val result = moviesRepository.getPopularMovies(10)
            assertEquals(
                NullPointerException().cause,
                (result as Result.Error).exception.cause
            )
        }
    }

    @Test
    fun `getTopRatedMovies return movies categorized by top rated from remote datasource`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(3)
            whenever(movieDataSource.getTopRatedMovies(3)).thenReturn(movies)
            whenever(localMovieDataSource.getMovies()).thenReturn(movies)

            val result = moviesRepository.getTopRatedMovies(3)
            assertEquals(Result.Success(movies), result)
        }
    }

    @Test
    fun `getTopRatedMovies fail when response is null and page number is 1`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(1)
            whenever(movieDataSource.getTopRatedMovies(1)).thenReturn(null)

            val result = moviesRepository.getTopRatedMovies(1)
            assertEquals(
                Exception(ServiceErrorEnum.NEW_CATEGORY_SELECTED.name).message,
                (result as Result.Error).exception.message
            )
        }
    }

    @Test
    fun `getTopRatedMovies fail when response is null and page number is greater than 1`() {
        runBlocking {
            whenever(localMovieDataSource.size()).thenReturn(10)
            whenever(movieDataSource.getTopRatedMovies(10)).thenReturn(null)

            val result = moviesRepository.getTopRatedMovies(10)
            assertEquals(
                NullPointerException().cause,
                (result as Result.Error).exception.cause
            )
        }
    }

    @Test
    fun `searchMovies return specific movies from remote datasource`() {
        runBlocking {
            whenever(movieDataSource.searchMovies("Ironman")).thenReturn(movies)

            val result = moviesRepository.searchMovies("Ironman")
            assertEquals(Result.Success(movies), result)
        }
    }

    @Test
    fun `searchMovies fail when response is null`() {
        runBlocking {
            whenever(movieDataSource.searchMovies("Ironman")).thenReturn(null)

            val result = moviesRepository.searchMovies("Ironman")
            assertEquals(Exception().cause, (result as Result.Error).exception.cause)
        }
    }

}