package com.pablogd.moviesapp.ui.modules.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.usescases.tvshows.GetPopularTvShows
import com.pablogd.domain.usescases.tvshows.GetTopRatedTvShows
import com.pablogd.domain.usescases.tvshows.GetTvShows
import com.pablogd.domain.usescases.tvshows.SaveTvShowDetail
import com.pablogd.moviesapp.ui.base.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TvShowsViewModel(
    private val getTvShows: GetTvShows,
    private val getPopularTvShows: GetPopularTvShows,
    private val getTopRatedTvShows: GetTopRatedTvShows,
    private val saveTvShowDetail: SaveTvShowDetail,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    sealed class UiModel {
        data class Loading(val showProgress: Boolean) : UiModel()
        data class Content(val tvShows: List<TvShow>) : UiModel()
        object Navigation : UiModel()
        data class Error(val error: String) : UiModel()
        object CategoryError : UiModel()
    }

    fun getTvShows() = launch {
        _model.value = UiModel.Loading(true)
        val result = getTvShows.invoke(1)
        _model.value = UiModel.Loading(false)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            _model.value = result.exception.message?.let { UiModel.Error(it) }
        }
    }

    fun getPopularTvShows(page: Int = 0) = launch {
        val result = getPopularTvShows.invoke(page)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            if (result.exception.message == ServiceErrorEnum.NEW_CATEGORY_SELECTED.name) {
                _model.value = UiModel.CategoryError
            }
            _model.value = result.exception.message?.let { UiModel.Error(it) }
        }
    }

    fun getTopRatedTvShows(page: Int = 0) = launch {
        val result = getTopRatedTvShows.invoke(page)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            if (result.exception.message == ServiceErrorEnum.NEW_CATEGORY_SELECTED.name) {
                _model.value = UiModel.CategoryError
            }
            _model.value = result.exception.message?.let { UiModel.Error(it) }
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