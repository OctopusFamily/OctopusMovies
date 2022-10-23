package com.octopus.moviesapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.moviesapp.util.Constants

@Entity(tableName = Constants.MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterImageUrl: String,
    val voteAverage: Float,
    val releaseDate: String,
)
