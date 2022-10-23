package com.octopus.moviesapp.di

import com.google.gson.Gson
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import android.content.Context
import com.octopus.moviesapp.data.remote.interceptor.AuthInterceptor
import com.octopus.moviesapp.util.ConnectionTracker
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.ConnectionTrackerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideGson(): Gson {
        return Gson()
    }


    @Singleton
    @Provides
    fun provideConnectionTracker(
        @ApplicationContext context: Context,
    ): ConnectionTracker {
        return ConnectionTrackerImpl(context)
    }
}








