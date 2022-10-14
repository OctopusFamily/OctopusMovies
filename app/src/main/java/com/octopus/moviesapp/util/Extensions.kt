package com.octopus.moviesapp.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.octopus.moviesapp.ui.trailer.TrailerActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.parse(this) ?: Date()
}

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}

fun String.buildImageUrl(): String {
    return Constants.IMAGE_BASE_URL + this
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.navigateToTrailerActivity(trailerKey: String) {
    Intent(this, TrailerActivity::class.java).run {
        putExtra(Constants.TRAILER_KEY, trailerKey)
        startActivity(this)
    }
}