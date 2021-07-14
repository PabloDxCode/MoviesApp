package com.pablogd.data.repositories

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.data.common.mockedMovie
import com.pablogd.data.common.mockedTvShow
import com.pablogd.data.common.mockedVideos
import com.pablogd.data.datasource.LocalDetailDataSource
import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.data.mappers.toDetailEntity
import com.pablogd.data.mappers.toDomain
import com.pablogd.domain.Result
import com.pablogd.domain.models.Video
import com.pablogd.domain.repositories.DetailRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.NullPointerException

@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryTest {

    @Mock
    private lateinit var localDetailDataSource: LocalDetailDataSource

    @Mock
    private lateinit var getMovieDataSource: MovieDataSource

    @Mock
    private lateinit var getTvShowDataSource: TvShowDataSource

    private lateinit var detailRepository: DetailRepository

    @Before
    fun setUp() {
        detailRepository =
            DetailRepositoryImpl(localDetailDataSource, getMovieDataSource, getTvShowDataSource)
    }

    @Test
    fun `saveDetail save movie detail into local datasource`() {
        runBlocking {
            val movie = mockedMovie.copy(1)
            whenever(localDetailDataSource.saveDetail(movie.toDetailEntity())).thenReturn(1)
            val result = detailRepository.saveDetail(movie)
            assertTrue((result as Result.Success).data)
        }
    }

    @Test
    fun `saveDetail can not save movie detail into local datasource`() {
        runBlocking {
            val movie = mockedMovie.copy(1)
            whenever(localDetailDataSource.saveDetail(movie.toDetailEntity())).thenReturn(0)
            val result = detailRepository.saveDetail(movie)
            assertFalse((result as Result.Success).data)
        }
    }

    @Test
    fun `saveDetail save tv show detail into local datasource`() {
        runBlocking {
            val tvShow = mockedTvShow.copy(1)
            whenever(localDetailDataSource.saveDetail(tvShow.toDetailEntity())).thenReturn(1)
            val result = detailRepository.saveDetail(tvShow)
            assertTrue((result as Result.Success).data)
        }
    }

    @Test
    fun `saveDetail can not save tv show detail into local datasource`() {
        runBlocking {
            val tvShow = mockedTvShow.copy(1)
            whenever(localDetailDataSource.saveDetail(tvShow.toDetailEntity())).thenReturn(0)
            val result = detailRepository.saveDetail(tvShow)
            assertFalse((result as Result.Success).data)
        }
    }

    @Test
    fun `getDetail return movie or tv show detail from local datasource`() {
        runBlocking {
            val detail = mockedMovie.copy(1).toDetailEntity()
            whenever(localDetailDataSource.getDetail()).thenReturn(detail)
            val result = detailRepository.getDetail()
            assertEquals(Result.Success(detail.toDomain()), result)
        }
    }

    @Test
    fun `getMovieVideos return movie videos from remote datasource`() {
        runBlocking {
            val videos = listOf(mockedVideos.copy("1"))
            whenever(getMovieDataSource.getVideos(1)).thenReturn(videos)
            val result = detailRepository.getMovieVideos(1)
            assertEquals(Result.Success(videos), result)
        }
    }

    @Test
    fun `getMovieVideos fail when response is not valid or null`() {
        runBlocking {
            val result = detailRepository.getMovieVideos(1)
            assertEquals(NullPointerException().cause, (result as Result.Error).exception.cause)
        }
    }

    @Test
    fun `getTvShowVideos return tv shows videos from remote datasource`() {
        runBlocking {
            val videos = listOf(mockedVideos.copy("1"))
            whenever(getTvShowDataSource.getVideos(1)).thenReturn(videos)
            val result = detailRepository.getTvShowVideos(1)
            assertEquals(Result.Success(videos), result)
        }
    }

    @Test
    fun `getTvShowVideos fail when response is not valid or null`() {
        runBlocking {
            val videos: List<Video>? = null
            whenever(getTvShowDataSource.getVideos(1)).thenReturn(videos)
            val result = detailRepository.getTvShowVideos(1)
            assertEquals(NullPointerException().cause, (result as Result.Error).exception.cause)
        }
    }

}