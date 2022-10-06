package com.octopus.moviesapp.data.remote.request

import com.octopus.moviesapp.data.remote.request.service.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val interceptor = MainInterceptor()

    private fun httpClient(): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val moviesApiService: MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
    val tvShowsApiService: TVShowsApiService by lazy { retrofit.create(TVShowsApiService::class.java) }
    val genresApiService: GenresApiService by lazy { retrofit.create(GenresApiService::class.java) }
    val searchApiService: SearchApiService by lazy { retrofit.create(SearchApiService::class.java) }
}