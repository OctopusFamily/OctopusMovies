package com.octopus.moviesapp.android.usecases.search

import com.octopus.moviesapp.models.model.SearchResult
import com.octopus.moviesapp.models.type.MediaType
import javax.inject.Inject

class FilterSearchResultsUseCase @Inject constructor() {
    operator fun invoke(results: List<SearchResult>, mediaType: MediaType): List<SearchResult> {
        return results.filter {
            it.mediaType == mediaType
        }
    }
}