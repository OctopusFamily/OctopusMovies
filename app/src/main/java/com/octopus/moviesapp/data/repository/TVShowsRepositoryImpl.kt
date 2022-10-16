package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.types.TVShowsCategory
import com.octopus.moviesapp.domain.mapper.*
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val castMapper: CastMapper,
    private val trailerMapper: TrailerMapper,
    private val tvShowDetailsMapper: TVShowDetailsMapper,
    private val tvShowMapper: TVShowMapper,
) : TVShowsRepository {
    override suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDetails {
        return tvShowDetailsMapper.map(tmdbApiService.getTVShowById(tvShowId))
    }

    override suspend fun getTVShowCastById(tvShowId: Int): List<Cast> {
        return castMapper.map(tmdbApiService.getTVShowCastById(tvShowId).itemsList)
    }

    override suspend fun getTVShowsTrailersById(tvShowId: Int): Trailer {
        return trailerMapper.map(tmdbApiService.getTVShowsTrailersById(tvShowId).items)
    }

    override suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow> {
        return tvShowMapper.map(tmdbApiService.getTVShowsByCategory(tvShowCategory.pathName, page).items)
    }

    override suspend fun getSearchTVShow(tvShowName: String): List<TVShow> {
        return tvShowMapper.map(tmdbApiService.getSearchTVShow(tvShowName).items)
    }
}