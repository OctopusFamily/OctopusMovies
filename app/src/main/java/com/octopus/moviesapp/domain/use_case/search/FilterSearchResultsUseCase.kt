package com.octopus.moviesapp.domain.use_case.search

import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class FilterSearchResultsUseCase @Inject constructor() {
    operator fun invoke(results: List<SearchResult>, mediaType: MediaType): List<SearchResult> {
        return results.filter {
            it.mediaType == mediaType
        }
    }
}