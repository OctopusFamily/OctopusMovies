package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.data.local.database.entity.TrendingEntity
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType

interface HomeRepository {
    suspend fun getTrendingMedia(mediaType: MediaType): List<TrendingEntity>
    suspend fun getTrendingMediaFromDB(mediaType: MediaType): List<Trending>
    suspend fun fetchTrendingMediaFromDB(mediaType: MediaType): List<TrendingEntity>
}