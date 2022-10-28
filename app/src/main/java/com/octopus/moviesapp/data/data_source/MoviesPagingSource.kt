package com.octopus.moviesapp.data.data_source

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.types.MoviesCategory
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesCategory: MoviesCategory,
    private val moviesMapper: MoviesMapper,
) : BasePagingSource<Movie>() {

    override suspend fun getData(page: Int): List<Movie> {
        return moviesMapper.map(tmdbApiService.getMoviesByCategory(moviesCategory.pathName, page).items)
    }
}