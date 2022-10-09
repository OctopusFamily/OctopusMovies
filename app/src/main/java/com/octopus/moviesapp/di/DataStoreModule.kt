package com.octopus.moviesapp.di

import android.content.Context
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.local.DataStorePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStorePreference(@ApplicationContext context: Context): DataStorePref {
        return DataStorePreferences(context)
    }
}