package com.octopus.moviesapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.octopus.moviesapp.data.local.datastore.DataStorePreferences
import com.octopus.moviesapp.data.local.datastore.DataStorePreferencesImpl
import com.octopus.moviesapp.util.Constants
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
    fun provideDataStorePreference(dataStore: DataStore<Preferences>): DataStorePreferences {
        return DataStorePreferencesImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.preferencesDataStore
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(Constants.SHARED_PREFERENCES_NAME)
}