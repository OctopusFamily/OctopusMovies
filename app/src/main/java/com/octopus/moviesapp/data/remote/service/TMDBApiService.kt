package com.octopus.moviesapp.data.remote.service

import com.octopus.moviesapp.data.remote.response.BaseResponse
import com.octopus.moviesapp.data.remote.response.CastResponse
import com.octopus.moviesapp.data.remote.response.GenresResponse
import com.octopus.moviesapp.data.remote.response.LogoutResponse
import com.octopus.moviesapp.data.remote.response.dto.*
import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.PersonDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.data.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.data.remote.response.lists.*
import com.octopus.moviesapp.data.remote.response.login.RequestTokenResponse
import com.octopus.moviesapp.data.remote.response.login.SessionResponse
import com.octopus.moviesapp.util.Constants
import retrofit2.Response
import retrofit2.http.*

interface TMDBApiService {

    // Movies End Points
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): MovieDTO

    @GET("movie/{movie_category}")
    suspend fun getMoviesByCategory(
        @Path("movie_category") moviesCategory: String,
        @Query("page") page: Int
    ): BaseResponse<MovieDTO>

    @GET("movie/{movieID}/videos")
    suspend fun getMovieTrailersById(
        @Path("movieID") movieId: Int
    ): BaseResponse<TrailerDTO>

    @GET("movie/{movieID}/credits")
    suspend fun getMovieCastById(
        @Path("movieID") movieId: Int
    ): CastResponse<CastDTO>

    // TVShows End Points
    @GET("tv/{tv_id}")
    suspend fun getTVShowById(
        @Path("tv_id") tvShowId: Int
    ): TVShowDTO

    @GET("tv/{tv_id}/credits")
    suspend fun getTVShowCastById(
        @Path("tv_id") tvShowId: Int
    ): CastResponse<CastDTO>

    @GET("tv/{tv_id}/videos")
    suspend fun getTVShowsTrailersById(
        @Path("tv_id") tvShowId: Int
    ): BaseResponse<TrailerDTO>

    @GET("tv/{tv_category}")
    suspend fun getTVShowsByCategory(
        @Path("tv_category") tvShowCategory: String,
        @Query("page") page: Int
    ): BaseResponse<TVShowDTO>

    // Genres End Points
    @GET("genre/{genre_type}/list")
    suspend fun getGenresByType(
        @Path("genre_type") genresType: String
    ): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenresId(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): BaseResponse<MovieDTO>

    @GET("discover/tv")
    suspend fun getTVShowsByGenresId(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): BaseResponse<TVShowDTO>

    @GET("person/{person_id}")
    suspend fun getPersonDetailsById(
        @Path("person_id") personId: Int
    ): PersonDTO

    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMoviesById(
        @Path("person_id") personId: Int
    ): CastResponse<MovieDTO>

    @GET("person/{person_id}/tv_credits")
    suspend fun getPersonTVShowsById(
        @Path("person_id") personId: Int
    ): CastResponse<TVShowDTO>

    @GET("authentication/token/new")
    suspend fun getRequestToken(): Response<RequestTokenResponse>

    @JvmSuppressWildcards
    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestTokenWithLogin(
        @FieldMap body: Map<String, Any>
    ): Response<RequestTokenResponse>

    @FormUrlEncoded
    @POST("authentication/session/new")
    suspend fun createSession(
        @Field("request_token") requestToken: String
    ): Response<SessionResponse>

    @FormUrlEncoded
    @POST("list")
    suspend fun createList(
        @Query("session_id") sessionId: String,
        @Field("name") name: String,
        @Field("description") description: String = Constants.EMPTY_TEXT
    ): CreateListResponse

    // Search End Points
    @GET("search/multi")
    suspend fun getSearchMultiMedia(
        @Query("query") query: String
    ): BaseResponse<SearchDTO>

    // Trending End Points
    @GET("trending/{media_type}/day")
    suspend fun getTrendingMedia(
        @Path("media_type") mediaType: String
    ): BaseResponse<TrendingDTO>

    @GET("account/{account_id}/lists")
    suspend fun getCreatedLists(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): BaseResponse<CreatedListsDto>

    @GET("list/{list_id}")
    suspend fun getList(
        @Path("list_id") listId: Int
    ): ListResponseDto<ListDetailsDto>

    @FormUrlEncoded
    @POST("list/{list_id}/add_item")
    suspend fun addMovieToList(
        @Path("list_id") ListId: Int,
        @Query("session_id") sessionId: String,
        @Field("media_id") movieId: Int
    ): AddMovieToListResponse

    @GET("account")
    suspend fun getAccountDetails(
        @Query("session_id") sessionId: String?
    ): AccountDTO

    @DELETE("authentication/session")
    suspend fun logout(
        @Query("session_id") sessionId: String
    ): LogoutResponse
}
