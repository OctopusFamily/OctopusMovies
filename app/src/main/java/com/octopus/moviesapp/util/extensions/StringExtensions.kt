package com.octopus.moviesapp.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.parse(this)?: Date()
}