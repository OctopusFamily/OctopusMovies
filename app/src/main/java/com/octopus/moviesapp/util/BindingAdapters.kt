package com.octopus.moviesapp.util

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.sealed.UiState
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}

@BindingAdapter(value = ["app:showWhenStateIsLoading"])
fun <T> showIfRequestStateIsLoading(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Loading
}

@BindingAdapter(value = ["app:showWhenStateIsSuccess"])
fun <T> showWhenStateIsSuccess(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Success
}

@BindingAdapter(value = ["app:showWhenStateIsError"])
fun <T> showWhenStateIsError(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Error
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter(value = ["app:releaseDate"])
fun setReleaseDate(view: TextView, date: Date) {
    val formatDate = SimpleDateFormat("yyyy")
    view.text = formatDate.format(date).toString()
}

@BindingAdapter(value = ["app:voteAverage"])
fun setVoteAverage(view: TextView, rating: Float) {
    view.text = String.format("%.1f", rating).toDouble().toString()
}

@BindingAdapter(value = ["app:setGenresItem"])
fun setGenresItem(view: TextView, items: List<Genre>) {
    view.text = items.joinToString(" | ") { itcs ->
        itcs.name
    }
    Log.d("SSDSDSD", "setGenresItem: ${items.joinToString(" | ") { itcs ->
        itcs.id.toString()
    }}")
}