package com.octopus.moviesapp.data.remote.request

import com.octopus.moviesapp.data.remote.response.GenresResponse
import com.octopus.moviesapp.data.remote.response.MultiItemsResponse
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Movies End Point
    @GET("/movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Response<MovieDTO>

    @GET("/movie/{movie_type}")
    suspend fun getMoviesByType(
        @Path("movie_type") moviesCategory: String,
        @Query("page") page: Int,
    ): Response<MultiItemsResponse<MovieDTO>>

    // TVShows End Point
    @GET("/tv/{tv_id}")
    suspend fun getTVShowById(
        @Path("tv_id") tvShowId: Int
    ): Response<TVShowDTO>

    @GET("/tv/{tv_type}")
    suspend fun getTvShowByType(
        @Path("tv_type") tvShowCategory: String,
        @Query("page") page: Int,
    ): Response<MultiItemsResponse<TVShowDTO>>

    // Genres End Point
    @GET("/genre/movie/list")
    suspend fun getMovieListGenres(): Response<GenresResponse>

    @GET("/genre/tv/list")
    suspend fun getTVShowListGenres(): Response<GenresResponse>
}