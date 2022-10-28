package com.octopus.moviesapp.ui.tv_show_details.tvShowCastState

import com.octopus.moviesapp.data.remote.response.dto.CastDTO
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.util.buildImageUrl

class TVShowCastUiStateMapper {
     fun map(input: List<Cast>): List<TVShowCastUiState> {
        return input.map {
            TVShowCastUiState(
                id = it.id ?: 0,
                name = it.name ?: "",
                profileImageUrl = buildImageUrl(it.profileImageUrl),
            )
        }
    }
}