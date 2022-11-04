package com.octopus.moviesapp.repositories.repository.tv_shows

import com.octopus.moviesapp.android.response.dto.CastDTO
import com.octopus.moviesapp.android.response.dto.TVShowDTO
import com.octopus.moviesapp.android.response.dto.TrailerDTO
import com.octopus.moviesapp.repositories.repository.type.TVShowsCategory

interface TVShowsRepository {
    suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDTO
    suspend fun getTVShowCastById(tvShowId: Int): List<CastDTO>
    suspend fun getTVShowsTrailersById(tvShowId: Int): List<TrailerDTO>
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShowDTO>
//    fun getTVShowPagingSource(tvShowsCategory: TVShowsCategory): TVShowsPagingSource
}