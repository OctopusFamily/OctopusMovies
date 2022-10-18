package com.octopus.moviesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.moviesapp.data.local.database.dao.MoviesDao
import com.octopus.moviesapp.data.local.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        const val DATABASE_NAME = "MOVIES_DATABASE"
    }

}