package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.mapper.TVShowMapper
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper,
    private val tvShowMapper: TVShowMapper,
) : MainRepository {

    override suspend fun getMoviesByCategory(moviesCategory: MoviesCategory): List<Movie> {
        return moviesMapper.map(
            apiService.getMoviesByCategory(moviesCategory.pathName, 1).body()!!.items
        )
    }

    override suspend fun getTVShowByCategory(
        tvShowCategory: TVShowsCategory,
        page: Int
    ): List<TVShow> {
        return tvShowMapper.map(
            apiService.getTvShowByCategory(tvShowCategory.pathName, 1).body()!!.items
        )
    }
}