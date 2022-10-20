package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface NestedCastListener : BaseInteractionListener {
    fun onCastClick(castId: Int)
}