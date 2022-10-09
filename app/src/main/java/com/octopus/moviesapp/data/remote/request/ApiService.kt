package com.octopus.moviesapp.data.remote.request

import com.octopus.moviesapp.data.remote.response.GenresResponse
import com.octopus.moviesapp.data.remote.response.MultiItemsResponse
import com.octopus.moviesapp.data.remote.response.SingleItemResponse
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
    ): Response<SingleItemResponse<MovieDTO>>

    @GET("/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<MovieDTO>>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<MovieDTO>>

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<MovieDTO>>

    @GET("/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<MultiItemsResponse<MovieDTO>>

    // TVShows End Point
    @GET("/tv/{yv_id}")
    suspend fun getTVShowById(
        @Path("movie_id") tvShowId: Int
    ): Response<SingleItemResponse<TVShowDTO>>

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