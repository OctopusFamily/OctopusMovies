package com.octopus.moviesapp.data.local.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.moviesapp.data.local.database.dao.MoviesDao
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.data.local.database.entity.TVShowEntity
import com.octopus.moviesapp.data.local.database.entity.TrendingEntity

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