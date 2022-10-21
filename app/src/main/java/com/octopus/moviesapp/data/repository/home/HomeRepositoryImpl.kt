package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.TrendingMapper
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val trendingMapper: TrendingMapper,
) : HomeRepository {
    override suspend fun getTrendingMedia(mediaType: MediaType): List<Trending> {
        return trendingMapper.mapList(tmdbApiService.getTrendingMedia(mediaType.mediaName).items)
    }

}