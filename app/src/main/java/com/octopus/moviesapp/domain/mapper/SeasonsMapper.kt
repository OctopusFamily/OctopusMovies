package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.SeasonDTO
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class SeasonsMapper @Inject constructor() : Mapper<SeasonDTO, Season>() {
    override fun map(input: SeasonDTO): Season {
        return Season(
            id = input.id ?: 0,
            seasonNumber = input.seasonNumber ?: 0,
            imageUrl = buildImageUrl(input.posterPath),
        )
    }
}