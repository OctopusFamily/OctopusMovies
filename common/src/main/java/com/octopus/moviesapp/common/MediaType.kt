package com.octopus.moviesapp.common

enum class MediaType(val mediaName: String) {
    MOVIE("movie"),
    TV("tv"),
    PERSON("person");

    companion object {
        fun fromMediaName(mediaName: String): com.octopus.moviesapp.android.local.types.MediaType {
            return values().associateBy(com.octopus.moviesapp.android.local.types.MediaType::mediaName)[mediaName] ?: throw IllegalArgumentException("No enum constant!")
        }
    }

}