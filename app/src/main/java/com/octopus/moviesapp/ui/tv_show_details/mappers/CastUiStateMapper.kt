package com.octopus.moviesapp.ui.tv_show_details.mappers

import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class CastUiStateMapper @Inject constructor() {
     fun map(input: List<Cast>): List<CastUiState> {
        return input.map {
            CastUiState(
                id = it.id ?: 0,
                name = it.name ?: "",
                profileImageUrl = buildImageUrl(it.profileImageUrl),
            )
        }
    }
}