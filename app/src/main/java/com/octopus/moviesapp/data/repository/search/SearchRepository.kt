package com.octopus.moviesapp.data.repository.search

import com.octopus.moviesapp.data.remote.response.dto.SearchDTO
import com.octopus.moviesapp.domain.model.SearchResult

interface SearchRepository {
    suspend fun getSearchMultiMedia(query: String): List<SearchDTO>
}