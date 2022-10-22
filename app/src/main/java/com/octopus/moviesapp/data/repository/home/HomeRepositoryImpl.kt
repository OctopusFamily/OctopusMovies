package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.data.local.database.dao.TrendingDao
import com.octopus.moviesapp.data.local.database.entity.TrendingEntity
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.TrendingMapper
import com.octopus.moviesapp.domain.mapper.local.TrendingEntityMapper
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val trendingEntityMapper: TrendingEntityMapper,
    private val trendingMapper: TrendingMapper,
    private val trendingDao: TrendingDao,
) : HomeRepository {
    override suspend fun getTrendingMedia(mediaType: MediaType): List<TrendingEntity> {
        return trendingEntityMapper.mapList(tmdbApiService.getTrendingMedia(mediaType.mediaName).items)
    }

    override suspend fun getTrendingMediaFromDB(mediaType: MediaType): List<Trending> {
        return trendingMapper.mapList(getTrendingMedia(mediaType))
    }

    override suspend fun fetchTrendingMediaFromDB(mediaType: MediaType): List<TrendingEntity> {
        return trendingDao.getAllTrending()
    }
}