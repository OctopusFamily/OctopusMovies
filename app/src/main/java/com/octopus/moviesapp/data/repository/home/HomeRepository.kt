package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.android.response.dto.TrendingDTO
import com.octopus.moviesapp.android.local.types.MediaType

interface HomeRepository {
    suspend fun getTrendingMedia(mediaType: MediaType): List<TrendingDTO>
}