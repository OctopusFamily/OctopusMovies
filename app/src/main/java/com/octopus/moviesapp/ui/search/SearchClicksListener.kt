package com.octopus.moviesapp.ui.search

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface SearchClicksListener : BaseInteractionListener {
    fun onItemClick(searchId: Int)
}