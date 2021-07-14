package com.pablogd.moviesapp.ui.modules.detail.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pablogd.domain.models.Video
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.FragmentDetailBinding
import com.pablogd.moviesapp.ui.base.BaseFragment
import com.pablogd.moviesapp.ui.modules.detail.activities.YoutubePlayer
import com.pablogd.moviesapp.ui.modules.detail.adapters.VideosAdapter

abstract class BaseDetailFragment : BaseFragment(R.layout.fragment_detail) {

    protected abstract fun getVideos()

    private lateinit var binding: FragmentDetailBinding

    private val adapter: VideosAdapter by lazy {
        VideosAdapter(listener)
    }

    private val listener: (Video) -> Unit = { video ->
        activity?.let {
            val intent = Intent(it, YoutubePlayer::class.java)
            intent.putExtra(YoutubePlayer.CUE_KEY, video.key)
            it.startActivity(intent)
            it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        initViews()
        getVideos()
    }

    private fun initViews() = with(binding) {
        rvVideos.adapter = adapter
    }

    protected fun setVideos(videos: List<Video>) {
        if(videos.isEmpty()){
            binding.rvVideos.visibility = View.GONE
            binding.tvWithoutVideos.visibility = View.VISIBLE
        } else {
            binding.rvVideos.visibility = View.VISIBLE
            binding.tvWithoutVideos.visibility = View.GONE
            adapter.items = videos
        }
    }

}