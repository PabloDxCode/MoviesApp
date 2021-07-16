package com.pablogd.moviesapp.ui.modules.searchable.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie
import com.pablogd.domain.usescases.movies.SaveMovieDetail
import com.pablogd.domain.usescases.searchable.SearchMovies
import com.pablogd.moviesapp.ui.base.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SearchMoviesViewModel(
    private val searchMovies: SearchMovies,
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
    }

    fun getSearch(query: String) = launch {
        val result = searchMovies.invoke(query)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            _model.value = UiModel.Error(result.exception.message ?: "")
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