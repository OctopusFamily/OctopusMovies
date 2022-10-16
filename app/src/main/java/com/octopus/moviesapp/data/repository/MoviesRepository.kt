package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.domain.types.MoviesCategory

interface MoviesRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getMovieDetailsById(movieId: Int): MovieDetails
    suspend fun getMovieTrailerById(movieId: Int): Trailer
    suspend fun getMovieCastById(movieId: Int): List<Cast>
    suspend fun searchMovie(movieName: String) : List<Movie>
    suspend fun getSearchMultiMedia(query: String): List<SearchResult>
}