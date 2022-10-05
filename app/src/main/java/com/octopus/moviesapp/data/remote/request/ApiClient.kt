package com.octopus.moviesapp.data.remote.request

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private fun httpClient(): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthInterceptor())
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}