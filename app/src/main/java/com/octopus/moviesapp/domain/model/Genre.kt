package com.octopus.moviesapp.domain.model

import android.os.Parcelable
import com.octopus.moviesapp.domain.types.GenresType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val id: Int,
    val name: String,
    val type: GenresType,
):Parcelable
