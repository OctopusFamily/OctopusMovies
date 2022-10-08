package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.data.remote.request.ApiClient
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie

class MainRepositoryImpl(): MainRepository {
    val api = ApiClient.apiService
    val moviesMapper = MoviesMapper()

    override suspend fun getPopularMovies(): List<Movie> {
        return moviesMapper.map(api.getPopularMovies(1).body()!!.items)
    }
}