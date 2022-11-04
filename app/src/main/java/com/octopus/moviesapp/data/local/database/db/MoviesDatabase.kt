package com.octopus.moviesapp.data.local.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.moviesapp.android.database.dao.MoviesDao
import com.octopus.moviesapp.android.database.entity.MovieEntity
import com.octopus.moviesapp.android.database.entity.TVShowEntity
import com.octopus.moviesapp.android.database.entity.TrendingEntity

@Database(
    entities = [
        MovieEntity::class,
        TrendingEntity::class,
        TVShowEntity::class,
    ], version = 1
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}