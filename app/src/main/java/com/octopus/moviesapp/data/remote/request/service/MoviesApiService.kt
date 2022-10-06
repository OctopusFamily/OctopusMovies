package com.octopus.moviesapp.data.remote.request.service

import com.octopus.moviesapp.data.remote.response.MultiItemsResponse
import com.octopus.moviesapp.data.remote.response.SingleItemResponse
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("/movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ) : Response<SingleItemResponse<MovieDTO>>

    @GET("/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<MovieDTO>>

    @GET("/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<MovieDTO>>

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<MovieDTO>>

    @GET("/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<MovieDTO>>
}