package com.octopus.moviesapp.domain.use_case.search

import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class FilterSearchResultsUseCase @Inject constructor() {
    operator fun invoke(results: List<SearchResult>, mediaType: MediaType): List<SearchResult> {
        return when (mediaType) {
            MediaType.MOVIE -> results.filter { it.mediaType == MediaType.MOVIE }
            MediaType.TV -> results.filter { it.mediaType == MediaType.TV }
            MediaType.PERSON -> results.filter { it.mediaType == MediaType.PERSON }
        }
    }

}