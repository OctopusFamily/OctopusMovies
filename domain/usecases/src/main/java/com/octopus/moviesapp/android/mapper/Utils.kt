package com.octopus.moviesapp.android.mapper

import java.text.SimpleDateFormat
import java.util.*

fun buildImageUrl(path: String?): String {
    return path?.let { IMAGE_BASE_URL + path } ?: ""
}

fun convertStringToDate(dateString: String?): Date {
    return if (dateString.isNullOrEmpty()) {
        Date()
    } else {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
        formatter.parse(dateString) ?: Date()
    }
}

 const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"

