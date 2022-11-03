package com.octopus.moviesapp.repositories.repository.home

import com.octopus.moviesapp.android.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.android.local.types.MediaType

interface HomeRepository {
    suspend fun getTrendingMedia(mediaType: MediaType): List<TrendingDTO>
}