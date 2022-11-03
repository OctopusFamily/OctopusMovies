package com.octopus.moviesapp.data.remote.pagingsource

import com.octopus.moviesapp.android.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.android.local.types.TVShowsCategory

class TVShowsPagingSource(
    private val tmdbApiService: TMDBApiService,
    private val tvShowsCategory: TVShowsCategory,
    private val tvShowsMapper: TVShowsMapper,
) : BasePagingSource<TVShow>() {
    override suspend fun getData(page: Int): List<TVShow> {
        return tvShowsMapper.mapList(tmdbApiService.getTVShowsByCategory(tvShowsCategory.pathName, page).items)
    }
}