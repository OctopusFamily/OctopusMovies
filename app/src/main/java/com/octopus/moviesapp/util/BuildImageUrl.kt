package com.octopus.moviesapp.util

fun String.buildImageUrl(): String {
    return Constants.IMAGE_BASE_URL + this
}