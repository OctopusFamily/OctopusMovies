package com.octopus.moviesapp.repositories.repository.home

import com.octopus.moviesapp.android.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.repositories.repository.type.MediaType

interface HomeRepository {
    suspend fun getTrendingMedia(mediaName: String): List<TrendingDTO>
}