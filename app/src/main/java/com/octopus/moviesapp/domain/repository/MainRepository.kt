package com.octopus.moviesapp.domain.repository

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.TVShow

interface MainRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory): List<Movie>
    suspend fun getTVShowByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
}