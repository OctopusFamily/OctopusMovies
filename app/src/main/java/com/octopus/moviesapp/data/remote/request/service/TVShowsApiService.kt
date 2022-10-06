package com.octopus.moviesapp.data.remote.request.service

import com.octopus.moviesapp.data.remote.response.MultiItemsResponse
import com.octopus.moviesapp.data.remote.response.SingleItemResponse
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowsApiService {

    @GET("/tv/{yv_id}")
    suspend fun getTVShowById(
        @Path("movie_id") tvShowId: Int
    ) : Response<SingleItemResponse<TVShowDTO>>

    @GET("/tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<TVShowDTO>>

    @GET("/tv/popular")
    suspend fun getPopularTVShows(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<TVShowDTO>>

    @GET("/tv/on_the_air")
    suspend fun getOnTheAirTVShows(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<TVShowDTO>>

    @GET("/tv/airing_today")
    suspend fun getAiringTodayTVShows(
        @Query("page") page: Int
    ) : Response<MultiItemsResponse<TVShowDTO>>
}