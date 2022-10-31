package com.octopus.moviesapp.data.repository.tv_shows

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsPagingSource
import com.octopus.moviesapp.data.remote.response.dto.CastDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.domain.types.TVShowsCategory

interface TVShowsRepository {
    suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDTO
    suspend fun getTVShowCastById(tvShowId: Int): List<CastDTO>
    suspend fun getTVShowsTrailersById(tvShowId: Int): List<TrailerDTO>
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShowDTO>
    fun getTVShowPagingSource(tvShowsCategory: TVShowsCategory): TVShowsPagingSource
}