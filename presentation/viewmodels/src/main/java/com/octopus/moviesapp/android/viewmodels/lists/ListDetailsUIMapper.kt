package com.octopus.moviesapp.android.viewmodels.lists

import com.octopus.moviesapp.models.model.ListDetails
import com.octopus.moviesapp.ui.lists.listsUIState.ListDetailsUIState


fun ListDetails.toListDetailsUIState(): ListDetailsUIState{
    return ListDetailsUIState(
        id = id,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        posterPath = posterPath,
    )
}