package com.octopus.moviesapp.repositories.repository.tv_shows

import com.octopus.moviesapp.android.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.android.remote.response.dto.CastDTO
import com.octopus.moviesapp.android.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.android.remote.service.TMDBApiService
import com.octopus.moviesapp.android.local.types.TVShowsCategory
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
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

//    override fun getTVShowPagingSource(tvShowsCategory: TVShowsCategory): TVShowsPagingSource {
//        return TVShowsPagingSource(tmdbApiService, tvShowsCategory, tvShowsMapper)
//    }

}