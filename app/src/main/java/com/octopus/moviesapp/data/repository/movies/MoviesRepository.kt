package com.octopus.moviesapp.data.repository.movies

import com.octopus.moviesapp.data.remote.response.dto.RatingDTO
import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.domain.types.MoviesCategory
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesRepository {
    suspend fun getMoviesByCategory(moviesCategory: MoviesCategory, page: Int): List<Movie>
    suspend fun getMovieDetailsById(movieId: Int): MovieDetails
    suspend fun getMovieTrailerById(movieId: Int): Trailer
    suspend fun getMovieCastById(movieId: Int): List<Cast>
    suspend fun getSearchMultiMedia(query: String): List<SearchResult>
    suspend fun ratingMovie(movieId: Int, sessionId: String, ratingValue: Float): RatingDTO
    suspend fun getRatedMovies(accountId: Int, sessionId: String): List<MovieDetails>
}