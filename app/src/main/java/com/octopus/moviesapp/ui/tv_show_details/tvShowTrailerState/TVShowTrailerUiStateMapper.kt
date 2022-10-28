package com.octopus.moviesapp.ui.tv_show_details.tvShowTrailerState

import com.octopus.moviesapp.domain.model.Trailer

class TVShowTrailerUiStateMapper {
     fun map(input: Trailer): TVShowTrailerUiState {
        return TVShowTrailerUiState(
        url = input.url
        )
    }
}