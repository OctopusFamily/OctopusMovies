package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.domain.types.MoviesCategory
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getMovieDetailsById(movieId: Int): MovieDetails
    suspend fun getMovieTrailerById(movieId: Int): Trailer
    suspend fun getMovieCastById(movieId: Int): List<Cast>
    suspend fun insertMovie(movieEntity: MovieEntity)
    fun getAllMovies() : Flow<MovieEntity>
}