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
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Response<MovieDTO>

    @GET("movie/{movie_category}")
    suspend fun getMoviesByCategory(
        @Path("movie_category") moviesCategory: String,
        @Query("page") page: Int,
    ): Response<MultiItemsResponse<MovieDTO>>

    // TVShows End Point
    @GET("tv/{tv_id}")
    suspend fun getTVShowById(
        @Path("tv_id") tvShowId: Int
    ): Response<TVShowDTO>

    @GET("tv/{tv_category}")
    suspend fun getTVShowsByCategory(
        @Path("tv_category") tvShowCategory: String,
        @Query("page") page: Int,
    ): Response<MultiItemsResponse<TVShowDTO>>

    @GET("/genre/{genre_list}")
    suspend fun getGenresByList(
        @Path("genre_list") genresList: String,
    ): Response<GenresResponse>
}