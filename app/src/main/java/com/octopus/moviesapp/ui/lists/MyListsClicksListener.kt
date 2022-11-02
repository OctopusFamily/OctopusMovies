package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.ui.base.BaseInteractionListener
import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState

interface MyListsClicksListener : BaseInteractionListener {
    fun onListClick(item: CreatedListsUIState)
}