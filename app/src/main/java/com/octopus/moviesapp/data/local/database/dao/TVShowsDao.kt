package com.octopus.moviesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.moviesapp.data.local.database.entity.MovieEntity
import com.octopus.moviesapp.data.local.database.entity.TVShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TVShowsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShows(tvShowsList: List<TVShowEntity>)

    @Query("SELECT * FROM TV_SHOW_TABLE")
    suspend fun getAllTVShows(): List<TVShowEntity>

    @Query("DELETE FROM TV_SHOW_TABLE")
    suspend fun deleteAllTVShows()
}