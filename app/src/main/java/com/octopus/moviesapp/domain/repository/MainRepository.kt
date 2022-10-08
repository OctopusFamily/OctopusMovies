package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.domain.model.Movie

interface MainRepository {
    suspend fun getPopularMovies(): List<Movie>
}