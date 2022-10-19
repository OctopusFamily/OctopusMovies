package com.octopus.moviesapp.ui.trailer

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.ActivityTrailerBinding
import com.octopus.moviesapp.util.Constants.TRAILER_KEY
import com.octopus.moviesapp.util.extensions.showShortToast

class TrailerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var _binding: ActivityTrailerBinding? = null
    private val binding: ActivityTrailerBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_trailer)
        playYoutubeVideo()
        initializeViews()
    }

    private fun initializeViews() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun playYoutubeVideo() {
        binding.youtubePlayerViewHome.initialize(getString(R.string.google_api_key), this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        intent.extras?.let { bundle ->
            p1?.loadVideo(bundle.getString(TRAILER_KEY), 0)
            p1?.setFullscreen(true)
            p1?.play()
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        showShortToast("Failed to play the video!")
    }
}