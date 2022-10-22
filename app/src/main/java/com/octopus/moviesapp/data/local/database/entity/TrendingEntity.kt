package com.octopus.moviesapp.data.local.database.entity

import androidx.room.Entity
import com.octopus.moviesapp.domain.types.MediaType

@Entity(tableName = "Trending_TABLE")
data class TrendingEntity(
    val id: Int,
    val imageUrl: String,
    val mediaType: MediaType,
)