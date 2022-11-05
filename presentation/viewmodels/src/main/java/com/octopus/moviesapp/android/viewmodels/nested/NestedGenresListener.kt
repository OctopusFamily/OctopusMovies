package com.octopus.moviesapp.android.viewmodels.nested

import com.octopus.moviesapp.android.viewmodels.base.BaseInteractionListener
import com.octopus.moviesapp.models.model.Genre

interface NestedGenresListener : BaseInteractionListener {
    fun onGenreClick(genre: Genre)
}