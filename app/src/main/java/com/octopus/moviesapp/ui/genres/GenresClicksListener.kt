package com.octopus.moviesapp.ui.genres

import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface GenresClicksListener : BaseInteractionListener {
    fun onGenreClick(genreId: Int, genreType: GenresType)
}