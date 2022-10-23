package com.octopus.moviesapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.util.Constants

@Entity(tableName = Constants.TRENDING_TABLE)
data class TrendingEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)