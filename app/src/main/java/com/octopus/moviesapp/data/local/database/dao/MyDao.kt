package com.octopus.moviesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.data.local.database.entity.TVShowEntity
import com.octopus.moviesapp.data.local.database.entity.TrendingEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrending(trendingList: List<TrendingEntity>)

    @Query("SELECT * FROM TRENDING_TABLE")
    suspend fun getAllTrending(): List<TrendingEntity>

    @Query("DELETE FROM TRENDING_TABLE")
    suspend fun deleteAllTrending()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieList: List<MovieEntity>)

    @Query("SELECT * FROM MOVIE_TABLE")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShows(tvShowsList: List<TVShowEntity>)

    @Query("SELECT * FROM TV_SHOW_TABLE")
    suspend fun getAllTVShows(): List<TVShowEntity>

    @Query("DELETE FROM TV_SHOW_TABLE")
    suspend fun deleteAllTVShows()
}