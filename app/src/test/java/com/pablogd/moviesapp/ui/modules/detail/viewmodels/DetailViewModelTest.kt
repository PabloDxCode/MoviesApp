package com.pablogd.moviesapp.ui.modules.detail.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.usescases.detail.GetDetail
import com.pablogd.moviesapp.common.mockedDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<DetailViewModel.UiModel>

    @Mock
    private lateinit var getDetail: GetDetail

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var detail: Detail

    @Before
    fun setUp() {
        detail = mockedDetail.copy(1)
        detailViewModel = DetailViewModel(getDetail, Dispatchers.Unconfined)
    }

    @Test
    fun `getDetail return data saved from movie or tv show flow`() {
        runBlocking {
            whenever(getDetail.invoke()).thenReturn(Result.Success(detail))
            detailViewModel.model.observeForever(observer)

            detailViewModel.getDetail()
            verify(getDetail).invoke()
            verify(observer).onChanged(DetailViewModel.UiModel.Content(detail))
        }
    }

}