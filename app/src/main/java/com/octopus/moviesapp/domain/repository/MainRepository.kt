package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesCategory

interface MainRepository {
    suspend fun getMoviesByType(moviesCategory : MoviesCategory): List<Movie>
}