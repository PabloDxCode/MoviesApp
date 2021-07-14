package com.pablogd.moviesapp.ui.modules.detail.fragments

import android.os.Bundle
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.detail.viewmodels.TvShowDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDetailFragment: BaseDetailFragment() {

    private lateinit var detail: Detail

    private val viewModel: TvShowDetailViewModel by viewModel()

    companion object {

        fun newInstance(bundle: Bundle): TvShowDetailFragment {
            val tvShowDetailFragment = TvShowDetailFragment()
            tvShowDetailFragment.arguments = bundle
            return tvShowDetailFragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detail = arguments?.getSerializable(DetailActivity.DETAIL_MODEL_KEY) as Detail
    }

    override fun getVideos() {
        viewModel.initScope()
        viewModel.model.observe(viewLifecycleOwner,::updateUi)
        viewModel.getVideos(detail.id)
    }

    private fun updateUi(model: TvShowDetailViewModel.UiModel){
        when(model){
            is TvShowDetailViewModel.UiModel.Loading -> showOrHideProgress(model.showProgress)
            is TvShowDetailViewModel.UiModel.Content -> setVideos(model.videos)
            is TvShowDetailViewModel.UiModel.Error -> baseView?.showError(model.error)
        }
    }

}