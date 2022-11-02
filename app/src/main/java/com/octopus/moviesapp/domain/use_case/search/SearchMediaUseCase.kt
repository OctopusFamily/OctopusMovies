package com.octopus.moviesapp.domain.use_case.search

import com.octopus.moviesapp.data.repository.search.SearchRepository
import com.octopus.moviesapp.domain.mapper.SearchResultMapper
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class SearchMediaUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchResultMapper: SearchResultMapper,
) {
    suspend operator fun invoke(query: String, mediaType: MediaType): List<SearchResult>{
        val result = if (query.isNotEmpty()) {
            searchResultMapper.mapList(searchRepository.getSearchMultiMedia(query))
        } else { emptyList() }
        return filterResultsBy(result,mediaType)
    }

    private fun filterResultsBy(results: List<SearchResult>,mediaType: MediaType): List<SearchResult> {
        return when (mediaType) {
            MediaType.MOVIE -> results.filter { it.mediaType == MediaType.MOVIE }
            MediaType.TV -> results.filter { it.mediaType == MediaType.TV }
            MediaType.PERSON -> results.filter { it.mediaType == MediaType.PERSON }
        }
    }
}