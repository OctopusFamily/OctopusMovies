package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.domain.model.Genre
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface NestedGenresListener : BaseInteractionListener {
    fun onGenreClick(genre: Genre)
}