package com.octopus.moviesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieList: List<MovieEntity>)

    @Query("SELECT * FROM MOVIE_TABLE")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAllMovies()
}