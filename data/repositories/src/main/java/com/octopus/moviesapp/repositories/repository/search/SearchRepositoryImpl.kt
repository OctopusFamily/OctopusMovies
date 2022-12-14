package com.octopus.moviesapp.repositories.repository.search

import com.octopus.moviesapp.android.response.dto.SearchDTO
import com.octopus.moviesapp.android.service.TMDBApiService

import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : SearchRepository {
    override suspend fun getSearchMultiMedia(query: String): List<SearchDTO> {
        return tmdbApiService.getSearchMultiMedia(query).items
    }
}