package com.pablogd.moviesapp.ui.modules.detail.activities

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

    private val customTarget = object : CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            binding.ivPoster.setImageBitmap(resource)
            applyPalette(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            // Empty method
        }
    }

    companion object {

        const val POSTER_TRANSITION_KEY = "poster"

        const val DETAIL_MODEL_KEY = "detailModel"

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
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w780${detail.backdropPath}")
                .placeholder(R.drawable.ic_placeholder)
                .into(customTarget)
        }
        binding.toolbar.title = detail.title
        binding.tvDetail.text =
            if (detail.detail.isEmpty()) getString(R.string.detail_without_overview) else detail.detail
        binding.tvDetailInfoView.setDetail(detail)

        val bundle = Bundle().apply {
            putSerializable(DETAIL_MODEL_KEY, detail)
        }
        val fragment = if (detail.section == SectionEnum.MOVIES.ordinal) {
            MovieDetailFragment.newInstance(bundle)
        } else {
            TvShowDetailFragment.newInstance(bundle)
        }
        loadFragmentDetail(fragment)
    }

    private fun applyPalette(resource: Bitmap){
        Palette.from(resource).generate { palette ->
            palette?.let {
                window.statusBarColor = it.getDarkMutedColor(R.attr.colorPrimaryDark)
                binding.collapsingToolbar.setStatusBarScrimColor(it.getMutedColor(R.attr.colorPrimaryDark))
                binding.collapsingToolbar.setContentScrimColor(it.getMutedColor(R.attr.colorPrimary))
            }
        }
    }

    private fun loadFragmentDetail(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

}