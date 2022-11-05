package com.octopus.moviesapp.android.ui.search

import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface SearchClicksListener : BaseInteractionListener {
    fun onItemClick(searchId: Int)
}