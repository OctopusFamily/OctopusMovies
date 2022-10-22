package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface MyListsClicksListener : BaseInteractionListener {
    fun onListClick(item: CreatedList)
}