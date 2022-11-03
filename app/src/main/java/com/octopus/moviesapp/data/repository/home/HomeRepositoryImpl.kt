package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.android.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.android.remote.service.TMDBApiService
import com.octopus.moviesapp.android.local.types.MediaType
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : HomeRepository {
    override suspend fun getTrendingMedia(mediaType: MediaType): List<TrendingDTO> {
        return tmdbApiService.getTrendingMedia(mediaType.mediaName).items
    }

}