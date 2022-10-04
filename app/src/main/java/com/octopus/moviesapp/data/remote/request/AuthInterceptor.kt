package com.octopus.moviesapp.data.remote.request

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter(API_KEY, key)
            .build()

        return chain.proceed(chain.request().newBuilder().url(original).build())
    }

    companion object {
        const val API_KEY = "api_key"
        const val key = "b9d3ba8b72ba567399d6d66e73ee78fa"
    }
}