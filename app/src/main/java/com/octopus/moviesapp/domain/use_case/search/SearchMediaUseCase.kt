package com.octopus.moviesapp.domain.use_case.search

import com.octopus.moviesapp.data.repository.search.SearchRepository
import com.octopus.moviesapp.domain.mapper.SearchResultMapper
import com.octopus.moviesapp.domain.model.SearchResult
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