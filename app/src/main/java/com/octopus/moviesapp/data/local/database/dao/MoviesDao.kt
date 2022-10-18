package com.octopus.moviesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAllMovies(): Flow<MovieEntity>
}