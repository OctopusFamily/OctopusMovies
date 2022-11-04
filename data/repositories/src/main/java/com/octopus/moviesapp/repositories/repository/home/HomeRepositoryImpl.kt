package com.octopus.moviesapp.repositories.repository.home

import com.octopus.moviesapp.android.response.dto.TrendingDTO
import com.octopus.moviesapp.android.service.TMDBApiService
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : HomeRepository {
    override suspend fun getTrendingMedia(mediaName: String): List<TrendingDTO> {
        return tmdbApiService.getTrendingMedia(mediaName).items
    }

}