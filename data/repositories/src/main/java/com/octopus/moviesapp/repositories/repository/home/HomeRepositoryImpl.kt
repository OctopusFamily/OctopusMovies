package com.octopus.moviesapp.repositories.repository.home

import com.octopus.moviesapp.android.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.android.remote.service.TMDBApiService
import com.octopus.moviesapp.android.local.types.MediaType
import com.octopus.moviesapp.repositories.repository.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : HomeRepository {
    override suspend fun getTrendingMedia(mediaName: String): List<TrendingDTO> {
        return tmdbApiService.getTrendingMedia(mediaName).items
    }

}