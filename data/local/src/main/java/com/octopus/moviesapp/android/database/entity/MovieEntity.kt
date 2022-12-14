package com.octopus.moviesapp.android.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.android.utils.Constants.MOVIE_TABLE

@Entity(tableName = MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: String,
)
