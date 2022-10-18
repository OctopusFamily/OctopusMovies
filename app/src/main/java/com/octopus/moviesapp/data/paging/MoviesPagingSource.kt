package com.octopus.moviesapp.data.paging

import com.octopus.moviesapp.data.remote.response.BaseResponse
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.types.MoviesCategory
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val moviesCategory: MoviesCategory,
) : BasePagingSource<MovieDTO>() {

    override suspend fun getData(): BaseResponse<MovieDTO> {
        return tmdbApiService.getMoviesByCategory(moviesCategory.pathName, queryPage)
    }
}