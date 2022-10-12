package com.octopus.moviesapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

fun String.buildYouTubeURL(): String {
    return Constants.YOUTUBE_BASE_URL + this
}