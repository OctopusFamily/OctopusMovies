package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.domain.mapper.Mapper
import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.ui.lists.listsUIState.ListDetailsUIState
import javax.inject.Inject

class ListDetailsUIMapper @Inject constructor() : Mapper<ListDetails, ListDetailsUIState>() {
    override fun map(input: ListDetails): ListDetailsUIState {
        return ListDetailsUIState(
            id = input.id,
            title = input.title,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            posterPath = input.posterPath,
        )
    }
}