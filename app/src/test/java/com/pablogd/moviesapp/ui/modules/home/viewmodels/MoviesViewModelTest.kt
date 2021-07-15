package com.pablogd.moviesapp.ui.modules.home.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.Movie
import com.pablogd.domain.usescases.movies.GetMovies
import com.pablogd.domain.usescases.movies.GetPopularMovies
import com.pablogd.domain.usescases.movies.GetTopRatedMovies
import com.pablogd.domain.usescases.movies.SaveMovieDetail
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
class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<MoviesViewModel.UiModel>

    @Mock
    private lateinit var getMovies: GetMovies

    @Mock
    private lateinit var getPopularMovies: GetPopularMovies

    @Mock
    private lateinit var getTopRatedMovies: GetTopRatedMovies

    @Mock
    private lateinit var saveMovieDetail: SaveMovieDetail

    private lateinit var moviesViewModel: MoviesViewModel

    private lateinit var movies: List<Movie>

    @Before
    fun setUp() {
        movies = listOf(mockedMovie.copy(1), mockedMovie.copy(2), mockedMovie.copy(3))
        moviesViewModel = MoviesViewModel(
            getMovies,
            getPopularMovies,
            getTopRatedMovies,
            saveMovieDetail,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `getMovies return initial data from database when app is started`() {
        runBlocking {
            whenever(getMovies.invoke(1)).thenReturn(Result.Success(movies))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getMovies()
            verify(getMovies).invoke(1)
            verify(observer).onChanged(MoviesViewModel.UiModel.Content(movies))
        }
    }

    @Test
    fun `getMovies fail and return error result`() {
        runBlocking {
            whenever(getMovies.invoke(1)).thenReturn(Result.Error(Exception()))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getMovies()
            verify(getMovies).invoke(1)
            verify(observer).onChanged(MoviesViewModel.UiModel.Error(Exception().message ?: ""))
        }
    }

    @Test
    fun `getPopularMovies return popular movies from db`() {
        runBlocking {
            whenever(getPopularMovies.invoke(0)).thenReturn(Result.Success(movies))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getPopularMovies(0)
            verify(getPopularMovies).invoke(0)
            verify(observer).onChanged(MoviesViewModel.UiModel.Content(movies))
        }
    }

    @Test
    fun `getPopularMovies fail and return category error`() {
        runBlocking {
            val messageError = ServiceErrorEnum.NEW_CATEGORY_SELECTED.name
            whenever(getPopularMovies.invoke(0)).thenReturn(Result.Error(Exception(messageError)))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getPopularMovies(0)
            verify(getPopularMovies).invoke(0)
            verify(observer).onChanged(MoviesViewModel.UiModel.Error(messageError))
        }
    }

    @Test
    fun `getPopularMovies fail and return error result`() {
        runBlocking {
            whenever(getPopularMovies.invoke(1)).thenReturn(Result.Error(Exception()))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getPopularMovies(1)
            verify(getPopularMovies).invoke(1)
            verify(observer).onChanged(MoviesViewModel.UiModel.Error(Exception().message ?: ""))
        }
    }

    @Test
    fun `getTopRatedMovies return popular movies from db`() {
        runBlocking {
            whenever(getTopRatedMovies.invoke(0)).thenReturn(Result.Success(movies))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getTopRatedMovies(0)
            verify(getTopRatedMovies).invoke(0)
            verify(observer).onChanged(MoviesViewModel.UiModel.Content(movies))
        }
    }

    @Test
    fun `getTopRatedMovies fail and return category error`() {
        runBlocking {
            val messageError = ServiceErrorEnum.NEW_CATEGORY_SELECTED.name
            whenever(getTopRatedMovies.invoke(0)).thenReturn(Result.Error(Exception(messageError)))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getTopRatedMovies(0)
            verify(getTopRatedMovies).invoke(0)
            verify(observer).onChanged(MoviesViewModel.UiModel.Error(messageError))
        }
    }

    @Test
    fun `getTopRatedMovies fail and return error result`() {
        runBlocking {
            whenever(getTopRatedMovies.invoke(1)).thenReturn(Result.Error(Exception()))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.getTopRatedMovies(1)
            verify(getTopRatedMovies).invoke(1)
            verify(observer).onChanged(MoviesViewModel.UiModel.Error(Exception().message ?: ""))
        }
    }

    @Test
    fun `saveItemClicked will save detail and navigate to detail view`() {
        runBlocking {
            val movie = movies[0]
            whenever(saveMovieDetail.invoke(movie)).thenReturn(Result.Success(true))
            moviesViewModel.model.observeForever(observer)

            moviesViewModel.saveItemClicked(movie)
            verify(saveMovieDetail).invoke(movie)
            verify(observer).onChanged(MoviesViewModel.UiModel.Navigation)
        }
    }

}