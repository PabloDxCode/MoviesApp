package com.pablogd.data.datasource.impl

import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.responses.*
import com.pablogd.data.responses.enqueueResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowDataSourceTest {

    private val mockWebServer = MockWebServer()

    private val moviesApiConfig = MockMoviesApiConfig(mockWebServer.url("/"))

    private lateinit var tvShowDataSource: TvShowDataSource

    @Before
    fun setUp() {
        tvShowDataSource = TvShowDataSourceImpl("apiKey", moviesApiConfig)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should get tv shows correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(TV_SHOWS_RESPONSE_CODE, 200)
            runBlocking {
                val actual = tvShowDataSource.getPopularTvShows(1)
                val expected = tvShowsObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should get top rated tv shows correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(TV_SHOWS_RESPONSE_CODE, 200)
            runBlocking {
                val actual = tvShowDataSource.getTopRatedTvShows(1)
                val expected = tvShowsObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should search tv shows correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(SEARCH_TV_SHOWS_RESPONSE_CODE, 200)
            runBlocking {
                val actual = tvShowDataSource.searchTvShows("Loki")
                val expected = searchTvShowsObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should get tv show videos correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse(VIDEOS_RESPONSE_CODE, 200)
            runBlocking {
                val actual = tvShowDataSource.getVideos(1)
                val expected = videosObjResponse.results.map { it.toDomain() }

                assertEquals(expected, actual)
            }
        }
    }

}