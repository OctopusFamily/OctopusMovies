package com.octopus.moviesapp.ui.person_details

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface PersonDetailsClicksListener : BaseInteractionListener {
    fun onPersonClick(personId: Int)
}