package com.octopus.moviesapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.sealed.UiState
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let { url ->
        Glide.with(imageView).load(url).into(imageView)
    }
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

@BindingAdapter(value = ["app:releaseDate"])
fun setReleaseDate(view: TextView, date: Date?) {
    val formatDate = SimpleDateFormat("yyyy", Locale("en"))
    date?.let {
        view.text = formatDate.format(it).toString()
    }
}

@BindingAdapter(value = ["app:voteAverage"])
fun setVoteAverage(view: TextView, rating: Float?) {
    rating?.let {
        view.text = String.format("%.1f", it).toDouble().toString()
    }
}

@BindingAdapter(value = ["app:setGenresItem"])
fun setGenresItem(view: TextView, items: List<Genre>) {
    view.text = items.joinToString(" | ") { itcs ->
        itcs.name
    }
}

@BindingAdapter(value = ["app:setRuntime"])
fun setRuntime(view: TextView, duration: Int?) {
    duration?.let {
        view.text = view.context.getString(R.string.duration, it)
    }
}

@BindingAdapter(value = ["app:setReviews"])
fun setReviews(view: TextView, reviews: Int?) {
    reviews?.let {
        view.text = it.toString()
    }
}

@BindingAdapter(value = ["app:seasonStatus"])
fun seasonStatus(view: TextView, seasonNumber: Int) {
    when (seasonNumber) {
        1 -> view.text = view.context.getString(R.string.season, seasonNumber)
        else -> view.text = view.context.getString(R.string.seasons, seasonNumber)
    }
}

@BindingAdapter(value = ["app:episodeStatus"])
fun episodeStatus(view: TextView, episodeNumber: Int) {
    when (episodeNumber) {
        1 -> view.text = view.context.getString(R.string.episode, episodeNumber)
        else -> view.text = view.context.getString(R.string.episodes, episodeNumber)
    }
}