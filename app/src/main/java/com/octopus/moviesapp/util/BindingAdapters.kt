package com.octopus.moviesapp.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.octopus.moviesapp.domain.sealed.UiState
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}

@BindingAdapter(value = ["app:showIfRequestStateIsLoading"])
fun <T> showIfRequestStateIsLoading(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Loading
}

@BindingAdapter(value = ["app:showIfRequestStateIsSuccess"])
fun <T> showIfRequestStateIsSuccess(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Success
}

@BindingAdapter(value = ["app:showIfRequestStateIsError"])
fun <T> showIfRequestStateIsError(view: View, uiState: UiState<T>) {
    view.isVisible = uiState is UiState.Error
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter(value = ["app:releaseDate"])
fun setReleaseDate (view: TextView, date: Date) {
    val formatDate = SimpleDateFormat("yyyy")
    view.text = formatDate.format(date).toString()
}

@BindingAdapter(value = ["app:voteAverage"])
fun setVoteAverage (view: TextView, rating: Float) {
    view.text = String.format("%.1f", rating).toDouble().toString()
}