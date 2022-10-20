package com.octopus.moviesapp.domain.model

import com.octopus.moviesapp.domain.types.SearchType
import java.util.*

data class SearchResult(
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: Date,
    val searchType: SearchType,
    val originalLanguage: String?,
    val overview: String?,
)
