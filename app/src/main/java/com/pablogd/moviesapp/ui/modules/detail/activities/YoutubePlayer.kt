package com.pablogd.moviesapp.ui.modules.detail.activities

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ActivityYoutubePlayerBinding

class YoutubePlayer : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var binding: ActivityYoutubePlayerBinding

    private var cue: String?= null

    companion object {
        const val CUE_KEY = "cue_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cue = intent.getStringExtra(CUE_KEY)
        if(cue == null){
            Toast.makeText(this, getString(R.string.youtube_player_error), Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.ytPlayer.initialize(getString(R.string.google_api_key), this)
        binding.ivClose.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        b: Boolean
    ) {
        youtubePlayer?.cueVideo(cue)
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?
    ) {
        Toast.makeText(this, getString(R.string.youtube_player_error), Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

}