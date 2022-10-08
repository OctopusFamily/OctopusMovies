package com.octopus.moviesapp.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel(), MoviesClicksListener {
    override fun onMovieClick(movieId: Int) {}

    fun onChipPopularCheck(checked: Boolean) {
        Log.i("LOG_TAG", checked.toString())
    }
}