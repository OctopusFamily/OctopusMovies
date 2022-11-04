package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.android.response.dto.lists.ListDetailsDTO
import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class ListDetailsMapper @Inject constructor() : Mapper<List<ListDetailsDTO>, List<ListDetails>>() {
    override fun map(input: List<ListDetailsDTO>): List<ListDetails> {
        return input.map {
            ListDetails(
                id = it.id ?: 0,
                title = it.title ?: "",
                releaseDate = it.releaseDate ?: "",
                voteAverage = it.voteAverage ?: 0.0,
                posterPath = buildImageUrl(it.posterPath),
            )
        }
    }
}