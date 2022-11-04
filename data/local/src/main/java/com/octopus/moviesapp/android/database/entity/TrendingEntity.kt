package com.octopus.moviesapp.android.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.android.utils.Constants.TRENDING_TABLE

@Entity(tableName = TRENDING_TABLE)
data class TrendingEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val mediaName: String,
)