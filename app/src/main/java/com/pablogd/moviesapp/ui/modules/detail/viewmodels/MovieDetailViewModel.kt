package com.pablogd.moviesapp.ui.modules.detail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablogd.domain.Result
import com.pablogd.domain.models.Video
import com.pablogd.domain.usescases.detail.GetMovieVideos
import com.pablogd.moviesapp.ui.base.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieVideos: GetMovieVideos,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    sealed class UiModel {
        data class Loading(val showProgress: Boolean) : UiModel()
        data class Content(val videos: List<Video>) : UiModel()
        data class Error(val error: String) : UiModel()
    }

    fun getVideos(movieId: Int) = launch {
        val result = getMovieVideos.invoke(movieId)
        if (result is Result.Success) {
            _model.value = UiModel.Content(result.data)
        } else if (result is Result.Error) {
            _model.value = UiModel.Error(result.exception.message ?: "")
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}