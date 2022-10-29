package com.octopus.moviesapp.data.repository.tv_shows

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsPagingSource
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.CastMapper
import com.octopus.moviesapp.domain.mapper.TVShowDetailsMapper
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.mapper.TrailerMapper
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.types.TVShowsCategory
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val castMapper: CastMapper,
    private val trailerMapper: TrailerMapper,
    private val tvShowDetailsMapper: TVShowDetailsMapper,
    private val tvShowsMapper: TVShowsMapper,
) : TVShowsRepository {
    override suspend fun getTVShowDetailsById(tvShowId: Int): TVShowDetails {
        return tvShowDetailsMapper.map(tmdbApiService.getTVShowById(tvShowId))
    }

    override suspend fun getTVShowCastById(tvShowId: Int): List<Cast> {
        return castMapper.map(tmdbApiService.getTVShowCastById(tvShowId).items)
    }

    override suspend fun getTVShowsTrailersById(tvShowId: Int): Trailer {
        return trailerMapper.map(tmdbApiService.getTVShowsTrailersById(tvShowId).items)
    }

    override suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShowDTO> {
        return tmdbApiService.getTVShowsByCategory(tvShowCategory.pathName, page).items
    }

    override fun getTVShowPagingSource(tvShowsCategory: TVShowsCategory): TVShowsPagingSource {
        return TVShowsPagingSource(tmdbApiService, tvShowsCategory, tvShowsMapper)
    }

}