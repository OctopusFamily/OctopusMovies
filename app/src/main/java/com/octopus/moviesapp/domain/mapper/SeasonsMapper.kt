package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.SeasonDTO
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class SeasonsMapper @Inject constructor() : Mapper<List<SeasonDTO>, List<Season>> {
    override fun map(input: List<SeasonDTO>): List<Season> {
        return if (input.isEmpty()) emptyList()
        else {
            input.map { seasonDTO ->
                Season(
                    id = seasonDTO.id ?: 0,
                    seasonNumber = seasonDTO.seasonNumber ?: 0,
                    imageUrl = buildImageUrl(seasonDTO.posterPath),
                )
            }
        }
    }
}