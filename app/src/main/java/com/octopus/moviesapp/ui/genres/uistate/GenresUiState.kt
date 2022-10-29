package com.octopus.moviesapp.ui.genres.uistate

import android.os.Parcelable
import com.octopus.moviesapp.domain.types.GenresType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresUiState(
    val id: Int,
    val name: String,
    val type: GenresType,
) : Parcelable