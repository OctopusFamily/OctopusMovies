package com.octopus.moviesapp.di.provide

import android.content.Context
import com.octopus.moviesapp.data.local.database.dao.MoviesDao
import com.octopus.moviesapp.data.local.database.db.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return MoviesDatabase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }
}