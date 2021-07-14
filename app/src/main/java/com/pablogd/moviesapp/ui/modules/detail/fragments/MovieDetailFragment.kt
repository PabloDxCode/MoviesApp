package com.pablogd.moviesapp.ui.modules.detail.fragments

import android.os.Bundle
import android.view.View
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentDetailBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.DetailActivity

class MovieDetailFragment : BaseFragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var detail: Detail

    companion object {

        fun newInstance(bundle: Bundle): MovieDetailFragment {
            val movieDetailFragment = MovieDetailFragment()
            movieDetailFragment.arguments = bundle
            return movieDetailFragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detail = arguments?.getSerializable(DetailActivity.DETAIL_MODEL) as Detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
    }

}