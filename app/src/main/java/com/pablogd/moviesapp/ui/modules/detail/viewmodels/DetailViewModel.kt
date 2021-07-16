package com.pablogd.moviesapp.ui.modules.detail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.usescases.detail.GetDetail
import com.pablogd.moviesapp.ui.base.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getDetail: GetDetail,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    sealed class UiModel {
        data class Loading(val showProgress: Boolean) : UiModel()
        data class Content(val detail: Detail) : UiModel()
        data class Error(val error: String) : UiModel()
    }

    fun getDetail() = launch {
        val result = getDetail.invoke()
        if(result is Result.Success){
            _model.value = UiModel.Content(result.data)
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}