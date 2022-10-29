package com.octopus.moviesapp.data.repository.tv_shows

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsPagingSource
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.types.TVShowsCategory

interface TVShowsRepository {
    suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDetails
    suspend fun getTVShowCastById(tvShowId: Int): List<Cast>
    suspend fun getTVShowsTrailersById(tvShowId: Int): Trailer
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
    fun getTVShowPagingSource(tvShowsCategory: TVShowsCategory): TVShowsPagingSource
}