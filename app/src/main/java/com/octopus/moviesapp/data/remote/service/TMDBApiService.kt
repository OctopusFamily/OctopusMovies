package com.octopus.moviesapp.data.remote.service

import com.octopus.moviesapp.data.remote.response.CastResponse
import com.octopus.moviesapp.data.remote.response.GenresResponse
import com.octopus.moviesapp.data.remote.response.MultiItemsResponse
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.PersonDTO
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
    ): MultiItemsResponse<MovieDTO>

    @GET("movie/{movieID}/videos")
    suspend fun getMovieTrailersById(
        @Path("movieID") movieId: Int,
    ): MultiItemsResponse<TrailerDTO>

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
    ): MultiItemsResponse<TrailerDTO>

    @GET("tv/{tv_category}")
    suspend fun getTVShowsByCategory(
        @Path("tv_category") tvShowCategory: String,
        @Query("page") page: Int,
    ): MultiItemsResponse<TVShowDTO>

    // Genres End Points
    @GET("genre/{genre_type}/list")
    suspend fun getGenresByType(
        @Path("genre_type") genresType: String,
    ): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenresId(
        @Query("with_genres") genreId: Int
    ): MultiItemsResponse<MovieDTO>

    @GET("discover/tv")
    suspend fun getTVShowsByGenresId(
        @Query("with_genres") genreId: Int
    ): MultiItemsResponse<TVShowDTO>

    // Search End Points
    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") movieName: String
    ): MultiItemsResponse<MovieDTO>

    @GET("search/tv")
    suspend fun getSearchTVShow(
        @Query("query") tvShowName: String
    ): MultiItemsResponse<TVShowDTO>

    @GET("search/person")
    suspend fun getSearchPerson(
        @Query("query") personName: String
    ): MultiItemsResponse<PersonDTO>
}