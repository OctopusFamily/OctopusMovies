package com.octopus.moviesapp.models.type

enum class MediaType(val mediaName: String) {
    MOVIE("movie"),
    TV("tv"),
    PERSON("person");

    companion object {
        fun fromMediaName(mediaName: String): MediaType {
            return values().associateBy(MediaType::mediaName)[mediaName] ?: throw IllegalArgumentException("No enum constant!")
        }
    }

}