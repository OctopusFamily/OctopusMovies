package com.octopus.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.octopus.moviesapp.data.local.database.dao.MyDao
import com.octopus.moviesapp.data.local.database.db.MyDatabase
import com.octopus.moviesapp.util.Constants
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
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: MyDatabase): MyDao {
        return database.moviesDao()
    }
}