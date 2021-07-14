package com.pablogd.moviesapp.ui.modules.detail.fragments

import android.os.Bundle
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity
import com.pablogd.moviesapp.ui.modules.detail.viewmodels.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseDetailFragment() {

    private lateinit var detail: Detail

    private val viewModel: MovieDetailViewModel by viewModel()

    companion object {

        fun newInstance(bundle: Bundle): MovieDetailFragment {
            val movieDetailFragment = MovieDetailFragment()
            movieDetailFragment.arguments = bundle
            return movieDetailFragment
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

    private fun updateUi(model: MovieDetailViewModel.UiModel){
        when(model){
            is MovieDetailViewModel.UiModel.Loading -> showOrHideProgress(model.showProgress)
            is MovieDetailViewModel.UiModel.Content -> setVideos(model.videos)
            is MovieDetailViewModel.UiModel.Error -> baseView?.showError(model.error)
        }
    }

}