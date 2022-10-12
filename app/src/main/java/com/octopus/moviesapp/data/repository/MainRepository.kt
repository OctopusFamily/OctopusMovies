package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.enums.MoviesCategory
import com.octopus.moviesapp.domain.enums.TVShowsCategory
import com.octopus.moviesapp.domain.model.*

interface MainRepository {
    // Movies Resources
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getMovieDetailsById(movieId: Int): MovieDetails
    suspend fun getMovieTrailerById(movieId: Int): Trailer
    suspend fun getMovieCastById(movieId: Int): List<Cast>

    // TV Shows Resources
    suspend fun getTVShowsByCategory(tvShowCategory: TVShowsCategory, page: Int): List<TVShow>

    // Genres Resources
    suspend fun getGenresByType(genresType: GenresType): List<Genre>
}