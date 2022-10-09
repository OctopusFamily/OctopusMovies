package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesType

interface MainRepository {
    suspend fun getMoviesByType(moviesType : MoviesType): List<Movie>
}