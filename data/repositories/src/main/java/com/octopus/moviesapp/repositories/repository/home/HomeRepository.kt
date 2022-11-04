package com.octopus.moviesapp.repositories.repository.home

import com.octopus.moviesapp.android.response.dto.TrendingDTO

interface HomeRepository {
    suspend fun getTrendingMedia(mediaName: String): List<TrendingDTO>
}