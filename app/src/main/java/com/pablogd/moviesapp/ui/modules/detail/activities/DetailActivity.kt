package com.pablogd.moviesapp.ui.modules.detail.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pablogd.domain.enums.SectionEnum
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ActivityDetailBinding
import com.pablogd.moviesapp.ui.base.BaseActivity
import com.pablogd.moviesapp.ui.modules.detail.fragments.MovieDetailFragment
import com.pablogd.moviesapp.ui.modules.detail.fragments.TvShowDetailFragment
import com.pablogd.moviesapp.ui.modules.detail.viewmodels.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    companion object {

        const val DETAIL_MODEL = "detailModel"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addObservers()
        viewModel.getDetail()
    }

    private fun addObservers() {
        viewModel.initScope()
        viewModel.model.observe(this, ::updateUi)
    }

    private fun updateUi(model: DetailViewModel.UiModel) {
        when (model) {
            is DetailViewModel.UiModel.Loading -> {
                //Empty
            }
            is DetailViewModel.UiModel.Content -> fillDetailInfo(model.detail)
            is DetailViewModel.UiModel.Error -> showError(model.error)
        }
    }

    private fun fillDetailInfo(detail: Detail) {
        binding.ivPoster.apply {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500${detail.backdropPath}")
                .placeholder(R.drawable.ic_placeholder)
                .into(this)
        }
        binding.toolbar.title = detail.title
        binding.tvDetail.text = detail.detail
        binding.tvDetailInfoView.setDetail(detail)

        val bundle = Bundle()
        bundle.putSerializable(DETAIL_MODEL, detail)
        val fragment = if (detail.section == SectionEnum.MOVIES.ordinal) {
            MovieDetailFragment.newInstance(bundle)
        } else {
            TvShowDetailFragment.newInstance(bundle)
        }
        loadFragmentDetail(fragment)
    }

    private fun loadFragmentDetail(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

}