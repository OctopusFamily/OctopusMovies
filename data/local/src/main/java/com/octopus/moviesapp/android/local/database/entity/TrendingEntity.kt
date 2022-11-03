package com.octopus.moviesapp.android.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.android.local.Constants.TRENDING_TABLE
import com.octopus.moviesapp.android.local.types.MediaType

@Entity(tableName = TRENDING_TABLE)
data class TrendingEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)