package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.data.remote.request.ApiClient
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesType

class MainRepositoryImpl(): MainRepository {
    val api = ApiClient.apiService
    val moviesMapper = MoviesMapper()

    override suspend fun getMoviesByType(moviesType: MoviesType): List<Movie> {
        return moviesMapper.map(api.getMoviesByType(1, moviesType).body()!!.items)
    }
}