package com.octopus.moviesapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.parse(this) ?: Date()
}


fun String.buildUrl(): String {
    return "https://image.tmdb.org/t/p/w342$this"
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