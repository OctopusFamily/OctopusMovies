package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.CastDTO
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.util.buildImageUrl
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


