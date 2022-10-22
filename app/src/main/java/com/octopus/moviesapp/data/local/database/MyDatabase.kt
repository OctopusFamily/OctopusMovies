package com.octopus.moviesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.moviesapp.data.local.database.dao.MoviesDao
import com.octopus.moviesapp.data.local.database.dao.TVShowsDao
import com.octopus.moviesapp.data.local.database.dao.TrendingDao
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.data.local.database.entity.TVShowEntity
import com.octopus.moviesapp.data.local.database.entity.TrendingEntity

@Database(
    entities = [MovieEntity::class, TVShowEntity::class, TrendingEntity::class,],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TVShowsDao
    abstract fun trendingDao(): TrendingDao

    companion object {
        const val DATABASE_NAME = "MY_DATABASE"
    }

}