package com.octopus.moviesapp.domain.types

enum class SearchType(val mediaType: String) {
    MOVIE("movie"),
    TV("tv"),
    PERSON("person");

    companion object {
        fun fromMediaType(mediaType: String): SearchType {
            return values().associateBy(SearchType::mediaType)[mediaType] ?: throw IllegalArgumentException("No enum constant!")
        }
    }

}