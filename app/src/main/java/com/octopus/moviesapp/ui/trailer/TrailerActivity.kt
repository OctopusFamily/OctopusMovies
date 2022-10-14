package com.octopus.moviesapp.ui.trailer

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.octopus.moviesapp.R
import com.octopus.moviesapp.databinding.ActivityTrailerBinding
import com.octopus.moviesapp.util.Constants.TRAILER_KEY

class TrailerActivity : YouTubeBaseActivity() {

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
        binding.youtubePlayerViewHome.initialize(
            "",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    val videoId = intent.extras!!.getString(TRAILER_KEY)
                    youTubePlayer.loadVideo(videoId, 0)
                    youTubePlayer.setFullscreen(true)
                    youTubePlayer.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Error in playing, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}