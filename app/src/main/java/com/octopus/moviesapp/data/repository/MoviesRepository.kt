package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.paging.MoviesPagingSource
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.domain.types.MoviesCategory

interface MoviesRepository {
//    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getMovieDetailsById(movieId: Int): MovieDetails
    suspend fun getMovieTrailerById(movieId: Int): Trailer
    suspend fun getMovieCastById(movieId: Int): List<Cast>

    fun getMovies(moviesCategory: MoviesCategory): MoviesPagingSource
}