package com.octopus.moviesapp.util.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.octopus.moviesapp.ui.trailer.TrailerActivity
import com.octopus.moviesapp.util.Constants

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.navigateToTrailerActivity(trailerKey: String) {
    Intent(this, TrailerActivity::class.java).run {
        putExtra(Constants.TRAILER_KEY, trailerKey)
        startActivity(this)
    }
}

fun Context.openUrlInBrowser(url: String) {
    Intent(Intent.ACTION_VIEW).run {
        data = Uri.parse(url)
        startActivity(this)
    }
}