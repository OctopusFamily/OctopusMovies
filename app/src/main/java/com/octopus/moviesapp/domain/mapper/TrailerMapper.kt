package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.util.buildUrlTrailerYoutube
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<List<TrailerDTO>, List<Trailer>> {
    override fun map(input: List<TrailerDTO>): List<Trailer> {
        return input.map {
            Trailer(
                trailerUrl = it.key?.buildUrlTrailerYoutube() ?: "",
            )
        }
    }
}