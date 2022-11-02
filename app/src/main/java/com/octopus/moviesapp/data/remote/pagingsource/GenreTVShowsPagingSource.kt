package com.octopus.moviesapp.data.remote.pagingsource

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.TVShow

class GenreTVShowsPagingSource(
    private val tmdbApiService: TMDBApiService,
    private val genreId: Int,
    private val tvShowsMapper: TVShowsMapper,
) : BasePagingSource<TVShow>() {
    override suspend fun getData(page: Int): List<TVShow> {
        return tvShowsMapper.mapList(tmdbApiService.getTVShowsByGenresId(genreId, page).items)
    }
}