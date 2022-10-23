package com.octopus.moviesapp.data.local.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.octopus.moviesapp.data.local.database.dao.MoviesDao
import com.octopus.moviesapp.data.local.database.entity.TrendingEntity

@Database(
    entities = [TrendingEntity::class], version = 1
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        private const val DATABASE_NAME = "octopusMovies.db"

        @Volatile
        private var instance: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MoviesDatabase {
            return Room.databaseBuilder(context, MoviesDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}