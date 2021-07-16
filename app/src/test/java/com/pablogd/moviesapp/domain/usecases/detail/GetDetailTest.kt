package com.pablogd.moviesapp.domain.usecases.detail

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.moviesapp.common.mockedDetail
import com.pablogd.domain.Result
import com.pablogd.domain.repositories.DetailRepository
import com.pablogd.domain.usescases.detail.GetDetail
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDetailTest {

    @Mock
    private lateinit var detailRepository: DetailRepository

    private lateinit var getDetail: GetDetail

    @Before
    fun setUp() {
        getDetail = GetDetail(detailRepository)
    }

    @Test
    fun `invoke get detail from get detail repository with success result`() {
        runBlocking {
            val detail = Result.Success(mockedDetail.copy(id = 1))
            whenever(detailRepository.getDetail()).thenReturn(detail)

            val result = getDetail.invoke()
            assertEquals(detail, result)
        }
    }

}