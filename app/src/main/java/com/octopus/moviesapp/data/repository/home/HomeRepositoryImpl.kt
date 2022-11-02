package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.data.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.TrendingMapper
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : HomeRepository {
    override suspend fun getTrendingMedia(mediaType: MediaType): List<TrendingDTO> {
        return tmdbApiService.getTrendingMedia(mediaType.mediaName).items
    }

}