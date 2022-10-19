package com.octopus.moviesapp.ui.actors

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface ActorsClicksListener : BaseInteractionListener {
    fun onActorClick(personId: Int)
}