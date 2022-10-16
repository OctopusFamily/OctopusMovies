package com.octopus.moviesapp.di

import android.content.Context
import com.octopus.moviesapp.util.NetworkStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class NetworkStateModule {

    @Provides
    fun provideNetworkState(@ApplicationContext context: Context): NetworkStateImpl =
        NetworkStateImpl(context)

}