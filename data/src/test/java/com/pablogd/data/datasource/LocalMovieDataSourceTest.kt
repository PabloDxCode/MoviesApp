package com.pablogd.data.datasource

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.data.common.mockedMovie
import com.pablogd.data.database.MoviesDB
import com.pablogd.data.database.daos.MovieDao
import com.pablogd.data.datasource.impl.LocalMovieDataSourceImpl
import com.pablogd.data.mappers.toMovieEntity
import com.pablogd.domain.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalMovieDataSourceTest {

    @Mock
    private lateinit var db: MoviesDB

    @Mock
    private lateinit var movieDao: MovieDao

    private lateinit var localMovieDataSource: LocalMovieDataSource

    private lateinit var movies: List<Movie>

    @Before
    fun setUp() {
        movies = listOf(mockedMovie.copy(1))
        whenever(db.movieDao()).thenReturn(movieDao)
        localMovieDataSource = LocalMovieDataSourceImpl(db, Dispatchers.IO)
    }

    @Test
    fun `size return number of items in movie db`() {
        runBlocking {
            whenever(movieDao.movieCount()).thenReturn(4)
            val result = localMovieDataSource.size()
            assertEquals(4, result)
        }
    }

    @Test
    fun `saveMovies should save movies and return state of item insert in db`() {
        runBlocking {
            whenever(movieDao.insertMovies(movies.map { it.toMovieEntity() })).thenReturn(arrayOf(1))
            val result = localMovieDataSource.saveMovies(movies)
            assertEquals(1, result.size)
        }
    }

    @Test
    fun `getMovies should return movies from db`() {
        runBlocking {
            whenever(movieDao.getAll()).thenReturn(movies.map { it.toMovieEntity() })
            val result = localMovieDataSource.getMovies()
            assertEquals(movies[0].title, result[0].title)
        }
    }

    @Test
    fun `findById should return movie by id from db`() {
        runBlocking {
            whenever(movieDao.findById(1)).thenReturn(movies[0].toMovieEntity())
            val result = localMovieDataSource.findById(1)
            assertEquals(movies[0].title, result.title)
        }
    }

    @Test
    fun `clearMovies should delete all movies and return state of item deleted in db`() {
        runBlocking {
            whenever(movieDao.clearMovies()).thenReturn(1)
            val result = localMovieDataSource.clearMovies()
            assertEquals(1, result)
        }
    }

}