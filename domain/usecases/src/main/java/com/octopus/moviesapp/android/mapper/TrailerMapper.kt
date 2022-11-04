package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.dto.TrailerDTO
import com.octopus.moviesapp.models.model.Trailer
import javax.inject.Inject

class TrailerMapper @Inject constructor() : Mapper<List<TrailerDTO>, Trailer>() {
    override fun map(input: List<TrailerDTO>): Trailer {
        val youtubeTrailer = input.find { it.site == YOUTUBE && !it.key.isNullOrEmpty() }
        return Trailer(youtubeTrailer?.key ?: "")
    }

    companion object{
        const val YOUTUBE = "YouTube"
    }
}