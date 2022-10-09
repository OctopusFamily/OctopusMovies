package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper
) : MainRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return moviesMapper.map(apiService.getPopularMovies(1).body()!!.items)
    }
}