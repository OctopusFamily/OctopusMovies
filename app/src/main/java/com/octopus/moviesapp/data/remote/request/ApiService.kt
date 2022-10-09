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

    @GET("/tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<TVShowDTO>>

    @GET("/tv/popular")
    suspend fun getPopularTVShows(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<TVShowDTO>>

    @GET("/tv/on_the_air")
    suspend fun getOnTheAirTVShows(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<TVShowDTO>>

    @GET("/tv/airing_today")
    suspend fun getAiringTodayTVShows(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<TVShowDTO>>

    // Genres End Point
    @GET("/genre/movie/list")
    suspend fun getMovieListGenres(): Response<GenresResponse>

    @GET("/genre/tv/list")
    suspend fun getTVShowListGenres(): Response<GenresResponse>
}