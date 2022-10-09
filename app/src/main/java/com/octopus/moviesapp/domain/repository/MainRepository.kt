package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesCategory

interface MainRepository {
    suspend fun getMoviesByCategory(moviesCategory : MoviesCategory): List<Movie>
}