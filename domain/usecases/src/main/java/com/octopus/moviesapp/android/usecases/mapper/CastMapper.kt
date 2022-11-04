package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.CastDTO
import com.octopus.moviesapp.models.model.Cast
import javax.inject.Inject

class CastMapper @Inject constructor() : Mapper<List<CastDTO>, List<Cast>>() {
    override fun map(input: List<CastDTO>): List<Cast> {
        return input.map {
            Cast(
                id = it.id ?: 0,
                name = it.name ?: "",
                profileImageUrl = buildImageUrl(it.profile_path),
            )
        }
    }
}


