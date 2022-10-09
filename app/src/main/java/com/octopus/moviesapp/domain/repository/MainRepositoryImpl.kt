package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.data.remote.request.ApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesType
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper
) : MainRepository {
    
    override suspend fun getMoviesByType(moviesType: MoviesType): List<Movie> {
        return moviesMapper.map(apiService.getMoviesByType(1, moviesType).body()!!.items)
    }
}