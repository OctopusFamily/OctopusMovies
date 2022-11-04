package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.dto.SeasonDTO
import com.octopus.moviesapp.models.model.Season
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