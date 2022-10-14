package com.octopus.moviesapp.ui.genres


import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface GenresClicksListener : BaseInteractionListener {
    fun onGenreClick(genre: Genre)
}