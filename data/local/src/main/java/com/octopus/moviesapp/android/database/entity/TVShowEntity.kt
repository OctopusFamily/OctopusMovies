package com.octopus.moviesapp.android.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.android.local.Constants.TV_SHOW_TABLE

@Entity(tableName = TV_SHOW_TABLE)
data class TVShowEntity(
    @PrimaryKey
    val id: Int,
    val movieID: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val started: String,
)