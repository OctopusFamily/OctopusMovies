package com.octopus.moviesapp.data.remote.request.service

import com.octopus.moviesapp.data.remote.response.GenresResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenresApiService {
    @GET("/genre/movie/list")
    suspend fun getMovieListGenres() : Response<GenresResponse>

    @GET("/genre/tv/list")
    suspend fun getTVShowListGenres() : Response<GenresResponse>
}