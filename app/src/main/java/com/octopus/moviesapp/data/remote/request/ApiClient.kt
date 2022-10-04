package com.octopus.moviesapp.data.remote.request

import com.octopus.moviesapp.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private fun httpClientTimeOut(): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClientTimeOut())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}