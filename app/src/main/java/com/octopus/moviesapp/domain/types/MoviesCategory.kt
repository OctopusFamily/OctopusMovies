package com.octopus.moviesapp.domain.types

enum class MoviesCategory(val pathName: String) {
    POPULAR("popular"),
    NOW_PLAYING("now_playing"),
    UPCOMING("upcoming"),
    TOP_RATED("top_rated"),
}