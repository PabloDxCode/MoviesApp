package com.pablogd.moviesapp.ui.modules.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.Movie
import com.pablogd.domain.usescases.movies.GetMovies
import com.pablogd.domain.usescases.movies.GetPopularMovies
import com.pablogd.domain.usescases.movies.GetTopRatedMovies
import com.pablogd.domain.usescases.movies.SaveMovieDetail
import com.pablogd.moviesapp.ui.base.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMovies: GetMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val saveMovieDetail: SaveMovieDetail,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    sealed class UiModel {
        data class Loading(val showProgress: Boolean) : UiModel()
        data class Content(val movies: List<Movie>) : UiModel()
        object Navigation : UiModel()
        data class Error(val error: String) : UiModel()
        object CategoryError : UiModel()
    }

    fun getMovies() = launch {
        _model.value = UiModel.Loading(true)
        val result = getMovies.invoke(1)
        _model.value = UiModel.Loading(false)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            _model.value = result.exception.message?.let { UiModel.Error(it) }
        }
    }

    fun getPopularMovies(page: Int = 0) = launch {
        val result = getPopularMovies.invoke(page)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            if(result.exception.message == ServiceErrorEnum.NEW_CATEGORY_SELECTED.name){
                _model.value = UiModel.CategoryError
            }
            _model.value = result.exception.message?.let { UiModel.Error(it) }
        }
    }

    fun getTopRatedMovies(page: Int = 0) = launch {
        val result = getTopRatedMovies.invoke(page)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            if(result.exception.message == ServiceErrorEnum.NEW_CATEGORY_SELECTED.name){
                _model.value = UiModel.CategoryError
            }
            _model.value = result.exception.message?.let { UiModel.Error(it) }
        }
    }

    fun saveItemClicked(movie: Movie) = launch {
        saveMovieDetail.invoke(movie)
        _model.value = UiModel.Navigation
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}