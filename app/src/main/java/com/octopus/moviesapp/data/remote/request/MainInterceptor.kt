package com.octopus.moviesapp.data.remote.request

import com.octopus.moviesapp.util.Constants.API_KEY
import com.octopus.moviesapp.util.Constants.API_KEY_QP
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MainInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter(API_KEY_QP, API_KEY)
            .build()

        return chain.proceed(chain.request().newBuilder().url(original).build())
    }
}