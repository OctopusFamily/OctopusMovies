package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.ui.base.BaseInteractionListener
import com.octopus.moviesapp.ui.genres.uistate.GenresUiState

interface GenresClicksListener : BaseInteractionListener {
    fun onGenreClick(genre: GenresUiState)
}