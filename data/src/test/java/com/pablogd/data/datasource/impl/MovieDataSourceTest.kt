package com.pablogd.data.datasource.impl

import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.responses.*
import com.pablogd.data.responses.enqueueResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MovieDataSourceTest {

    private val mockWebServer = MockWebServer()

    private val moviesApiConfig = MockMoviesApiConfig(mockWebServer.url("/"))

    private lateinit var movieDataSource: MovieDataSource

    @Before
    fun setUp() {
        movieDataSource = MovieDataSourceImpl("apiKey", moviesApiConfig)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should get movies correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(MOVIES_RESPONSE_CODE, 200)
            runBlocking {
                val actual = movieDataSource.getPopularMovies(1)
                val expected = movieObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should get top rated movies correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(MOVIES_RESPONSE_CODE, 200)
            runBlocking {
                val actual = movieDataSource.getTopRatedMovies(1)
                val expected = movieObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should search movies correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(SEARCH_MOVIES_RESPONSE_CODE, 200)
            runBlocking {
                val actual = movieDataSource.searchMovies("Iron Man")
                val expected = searchMoviesObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should get movie videos correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(VIDEOS_RESPONSE_CODE, 200)
            runBlocking {
                val actual = movieDataSource.getVideos(1)
                val expected = videosObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

}