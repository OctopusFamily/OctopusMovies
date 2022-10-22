package com.octopus.moviesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.moviesapp.data.local.database.entity.TrendingEntity

@Dao
interface TrendingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrending(trendingList: List<TrendingEntity>)

    @Query("SELECT * FROM TRENDING_TABLE")
    suspend fun getAllTrending(): List<TrendingEntity>

    @Query("DELETE FROM TRENDING_TABLE")
    suspend fun deleteAllTrending()
}