package com.octopus.moviesapp.util.extensions

fun String.isEnglishLettersOnly(): Boolean {
    return this.matches("^[a-zA-Z]*$".toRegex())
}

fun String.isEnglishLettersAndDigitsOnly(): Boolean {
    return this.matches("^[a-zA-Z0-9]*$".toRegex())
}