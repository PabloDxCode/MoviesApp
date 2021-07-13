package com.pablogd.moviesapp.ui.modules.detail.fragments

import android.os.Bundle
import android.view.View
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentDetailBinding
import com.pablogd.moviesapp.ui.base.BaseFragment

class TvShowDetailFragment: BaseFragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var detail: Detail

    companion object {

        fun newInstance(bundle: Bundle): TvShowDetailFragment {
            val tvShowDetailFragment = TvShowDetailFragment()
            tvShowDetailFragment.arguments = bundle
            return tvShowDetailFragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        initViews()
    }

    private fun initViews() = with(binding) {
        tvDetail.text = detail.detail
        tvDetailInfoView.setDetail(detail)
    }

}