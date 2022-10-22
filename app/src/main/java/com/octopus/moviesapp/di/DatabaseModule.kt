package com.octopus.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.octopus.moviesapp.data.local.database.MyDatabase
import com.octopus.moviesapp.data.local.database.dao.MoviesDao
import com.octopus.moviesapp.data.local.database.dao.TVShowsDao
import com.octopus.moviesapp.data.local.database.dao.TrendingDao
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
    fun provideMoviesDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(myDatabase: MyDatabase): MoviesDao {
        return myDatabase.moviesDao()
    }

    @Singleton
    @Provides
    fun provideTVShowsDao(myDatabase: MyDatabase): TVShowsDao {
        return myDatabase.tvShowsDao()
    }

    @Singleton
    @Provides
    fun provideTrendingDao(myDatabase: MyDatabase): TrendingDao {
        return myDatabase.trendingDao()
    }
}
