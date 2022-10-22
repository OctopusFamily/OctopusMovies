package com.octopus.moviesapp.data.repository.home

import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType

interface HomeRepository {
    suspend fun getTrendingMedia(mediaType: MediaType): List<Trending>
}