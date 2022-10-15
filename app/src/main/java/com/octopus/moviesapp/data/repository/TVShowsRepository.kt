package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.domain.types.TVShowsCategory
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer

interface TVShowsRepository {
    suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDetails
    suspend fun getTVShowCastById(tvShowId: Int): List<Cast>
    suspend fun getTVShowsTrailersById(tvShowId: Int): Trailer
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
}