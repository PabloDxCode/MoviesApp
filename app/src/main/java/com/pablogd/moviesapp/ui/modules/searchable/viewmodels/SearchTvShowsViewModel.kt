package com.pablogd.moviesapp.ui.modules.searchable.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablogd.domain.Result
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.usescases.searchable.SearchTvShows
import com.pablogd.domain.usescases.tvshows.SaveTvShowDetail
import com.pablogd.moviesapp.ui.base.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SearchTvShowsViewModel(
    private val searchTvShows: SearchTvShows,
    private val saveTvShowDetail: SaveTvShowDetail,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    sealed class UiModel {
        data class Loading(val showProgress: Boolean) : UiModel()
        data class Content(val movies: List<TvShow>) : UiModel()
        object Navigation : UiModel()
        data class Error(val error: String) : UiModel()
    }

    fun getSearch(query: String) = launch {
        val result = searchTvShows.invoke(query)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            _model.value = UiModel.Error(result.exception.message ?: "")
        }
    }

    fun saveItemClicked(tvShow: TvShow) = launch {
        saveTvShowDetail.invoke(tvShow)
        _model.value = UiModel.Navigation
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}