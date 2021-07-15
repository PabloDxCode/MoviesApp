package com.pablogd.moviesapp.ui.modules.searchable.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie
import com.pablogd.domain.usescases.movies.SaveMovieDetail
import com.pablogd.domain.usescases.searchable.SearchMovies
import com.pablogd.moviesapp.common.mockedMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class SearchMoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<SearchMoviesViewModel.UiModel>

    @Mock
    private lateinit var searchMovies: SearchMovies

    @Mock
    private lateinit var saveMovieDetail: SaveMovieDetail

    private lateinit var viewModel: SearchMoviesViewModel

    private lateinit var movies: List<Movie>

    @Before
    fun setUp() {
        movies = listOf(mockedMovie.copy(1), mockedMovie.copy(2))
        viewModel = SearchMoviesViewModel(searchMovies, saveMovieDetail, Dispatchers.Unconfined)
    }

    @Test
    fun `getSearch return list of movies by query from remote service`() {
        runBlocking {
            whenever(searchMovies.invoke("IronMan")).thenReturn(Result.Success(movies))
            viewModel.model.observeForever(observer)

            viewModel.getSearch("IronMan")
            verify(searchMovies).invoke("IronMan")
            verify(observer).onChanged(SearchMoviesViewModel.UiModel.Content(movies))
        }
    }

    @Test
    fun `getSearch fail and return error result`() {
        runBlocking {
            whenever(searchMovies.invoke("Black Widow")).thenReturn(Result.Error(Exception()))
            viewModel.model.observeForever(observer)

            viewModel.getSearch("Black Widow")
            verify(observer).onChanged(
                SearchMoviesViewModel.UiModel.Error(
                    Exception().message ?: ""
                )
            )
        }
    }

    @Test
    fun `saveTvShowDetail will save detail and navigate to detail view`() {
        runBlocking {
            val movie = movies[0]
            whenever(saveMovieDetail.invoke(movie)).thenReturn(Result.Success(true))
            viewModel.model.observeForever(observer)

            viewModel.saveItemClicked(movie)
            verify(saveMovieDetail).invoke(movie)
            verify(observer).onChanged(SearchMoviesViewModel.UiModel.Navigation)
        }
    }

}