package com.octopus.moviesapp.data.remote.service

import com.octopus.moviesapp.data.remote.response.CastResponse
import com.octopus.moviesapp.data.remote.response.GenresResponse
import com.octopus.moviesapp.data.remote.response.BaseResponse
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {

    // Movies End Points
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
    ): MovieDTO

    @GET("movie/{movie_category}")
    suspend fun getMoviesByCategory(
        @Path("movie_category") moviesCategory: String,
        @Query("page") page: Int,
    ): BaseResponse<MovieDTO>

    @GET("movie/{movieID}/videos")
    suspend fun getMovieTrailersById(
        @Path("movieID") movieId: Int,
    ): BaseResponse<TrailerDTO>

    @GET("movie/{movieID}/credits")
    suspend fun getMovieCastById(
        @Path("movieID") movieId: Int,
    ): CastResponse

    // TVShows End Points
    @GET("tv/{tv_id}")
    suspend fun getTVShowById(
        @Path("tv_id") tvShowId: Int
    ): TVShowDTO

    @GET("tv/{tv_id}/credits")
    suspend fun getTVShowCastById(
        @Path("tv_id") tvShowId: Int,
    ): CastResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTVShowsTrailersById(
        @Path("tv_id") tvShowId: Int,
    ): BaseResponse<TrailerDTO>

    @GET("tv/{tv_category}")
    suspend fun getTVShowsByCategory(
        @Path("tv_category") tvShowCategory: String,
        @Query("page") page: Int,
    ): BaseResponse<TVShowDTO>

    // Genres End Points
    @GET("genre/{genre_type}/list")
    suspend fun getGenresByType(
        @Path("genre_type") genresType: String,
    ): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenresId(
        @Query("with_genres") genreId: Int
    ): BaseResponse<MovieDTO>

    @GET("discover/tv")
    suspend fun getTVShowsByGenresId(
        @Query("with_genres") genreId: Int
    ): BaseResponse<TVShowDTO>
}