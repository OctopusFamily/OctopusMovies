package com.octopus.moviesapp.util

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.parse(this) ?: Date()
}


fun String.buildUrl(): String {
    return "https://image.tmdb.org/t/p/w342$this"
}