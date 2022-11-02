package com.octopus.moviesapp.data.repository.search

import com.octopus.moviesapp.data.remote.response.dto.SearchDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService

import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : SearchRepository {
    override suspend fun getSearchMultiMedia(query: String): List<SearchDTO> {
        return tmdbApiService.getSearchMultiMedia(query).items
    }
}