package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.domain.model.Trailer
import com.octopus.moviesapp.util.Constants
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<List<TrailerDTO>, Trailer>() {
    override fun map(input: List<TrailerDTO>): Trailer {
        val youtubeTrailer = input.find { it.site == Constants.YOUTUBE && !it.key.isNullOrEmpty() }
        return Trailer(youtubeTrailer?.key ?: "")
    }
}