package com.octopus.moviesapp.android.usecases.search

import com.octopus.moviesapp.android.usecases.mapper.SearchResultMapper
import com.octopus.moviesapp.models.model.SearchResult
import com.octopus.moviesapp.repositories.repository.search.SearchRepository
import javax.inject.Inject

class SearchMediaUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchResultMapper: SearchResultMapper,
) {
    suspend operator fun invoke(query: String): List<SearchResult> {
        return if (query.isNotEmpty()) {
            searchResultMapper.mapList(searchRepository.getSearchMultiMedia(query))
        } else {
            emptyList()
        }
    }

}