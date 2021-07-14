package com.pablogd.domain.usescases.tvshows

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.common.mockedTvShow
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.DetailRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveTvShowDetailTest {

    @Mock
    private lateinit var detailRepository: DetailRepository

    private lateinit var saveTvShowDetail: SaveTvShowDetail

    @Before
    fun setUp() {
        saveTvShowDetail = SaveTvShowDetail(detailRepository)
    }

    @Test
    fun `invoke save tv show detail from detail repository with success result`() {
        runBlocking {
            val tvShow = mockedTvShow.copy(1)
            val response = Result.Success(true)
            whenever(detailRepository.saveDetail(tvShow)).thenReturn(response)
            val result = saveTvShowDetail.invoke(tvShow)
            assertEquals(response, result)
        }
    }

    @Test
    fun `invoke save tv show detail from detail repository with error result`() {
        runBlocking {
            val tvShow = mockedTvShow.copy(1)
            val response = Result.Success(false)
            whenever(detailRepository.saveDetail(tvShow)).thenReturn(response)
            val result = saveTvShowDetail.invoke(tvShow)
            assertEquals(response, result)
        }
    }
}