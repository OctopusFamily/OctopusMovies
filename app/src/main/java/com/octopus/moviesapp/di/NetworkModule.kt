package com.octopus.moviesapp.di

import android.content.Context
import com.octopus.moviesapp.data.remote.interceptor.AuthInterceptor
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.InternetState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTMDBApiService(
        retrofit: Retrofit,
    ): TMDBApiService {
        return retrofit.create(TMDBApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    @Named("networkStatesRequest")
    fun networkStatesRequest(
    ): Request {
        return Request.Builder().url(Constants.URL_INTERNET_CHECKER)
            .method("GET", null).build()
    }


    @Singleton
    @Provides
    fun networkState(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
        @Named("networkStatesRequest") request: Request
    ): InternetState {
        return InternetState(context, okHttpClient, request)
    }


}







