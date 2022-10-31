package com.octopus.moviesapp.data.repository.tv_shows

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsPagingSource
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.data.remote.response.dto.CastDTO
import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.types.TVShowsCategory
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val tvShowsMapper: TVShowsMapper,
) : TVShowsRepository {
    override suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDTO {
        return tmdbApiService.getTVShowById(tvShowId)
    }

    override suspend fun getTVShowCastById(tvShowId: Int): List<CastDTO> {
        return tmdbApiService.getTVShowCastById(tvShowId).items
    }

    override suspend fun getTVShowsTrailersById(tvShowId: Int): List<TrailerDTO> {
        return tmdbApiService.getTVShowsTrailersById(tvShowId).items
    }

    override suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShowDTO> {
        return tmdbApiService.getTVShowsByCategory(tvShowCategory.pathName, page).items
    }

    override fun getTVShowPagingSource(tvShowsCategory: TVShowsCategory): TVShowsPagingSource {
        return TVShowsPagingSource(tmdbApiService, tvShowsCategory, tvShowsMapper)
    }

}