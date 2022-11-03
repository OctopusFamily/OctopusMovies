package com.octopus.moviesapp.repositories.repository.movies

import com.octopus.moviesapp.android.remote.response.dto.CastDTO
import com.octopus.moviesapp.android.remote.response.dto.MovieDTO
import com.octopus.moviesapp.android.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.android.local.types.MoviesCategory

interface MoviesRepository {
    suspend fun getMovieDetailsById(movieId: Int): MovieDTO
    suspend fun getMovieTrailerById(movieId: Int): List<TrailerDTO>
    suspend fun getMovieCastById(movieId: Int): List<CastDTO>
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<MovieDTO>
    //fun getMoviesPagingSource(moviesCategory: MoviesCategory): MoviesPagingSource
}