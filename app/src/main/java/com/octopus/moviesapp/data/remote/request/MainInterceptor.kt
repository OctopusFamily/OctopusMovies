package com.octopus.moviesapp.data.remote.request

import okhttp3.Interceptor
import okhttp3.Response

class MainInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter(API_KEY_QP, API_KEY)
            .build()

        return chain.proceed(chain.request().newBuilder().url(original).build())
    }

    companion object {
        const val API_KEY_QP = "api_key"
        const val API_KEY = "b9d3ba8b72ba567399d6d66e73ee78fa"
    }
}