package com.octopus.moviesapp.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    return formatter.parse(this) ?: Date()
}

fun String.isEnglishLettersOnly(): Boolean {
    return this.matches("^[a-zA-Z]*$".toRegex())
}

fun String.isEnglishLettersAndDigitsOnly(): Boolean {
    return this.matches("^[a-zA-Z0-9]*$".toRegex())
}