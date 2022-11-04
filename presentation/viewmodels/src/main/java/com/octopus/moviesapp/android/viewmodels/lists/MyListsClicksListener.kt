package com.octopus.moviesapp.android.viewmodels.lists

import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState

interface MyListsClicksListener : BaseInteractionListener {
    fun onListClick(item: CreatedListsUIState)
}