package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.domain.model.TVShow

interface MainRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>
    suspend fun getGenresByType(genresType: GenresType): List<Genre>
}