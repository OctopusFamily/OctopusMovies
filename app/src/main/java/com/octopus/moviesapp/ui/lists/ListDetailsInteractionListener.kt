package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.ui.base.BaseInteractionListener
import com.octopus.moviesapp.ui.lists.listsUIState.ListDetailsUIState

interface ListDetailsInteractionListener : BaseInteractionListener {
    fun onItemClick(item: ListDetailsUIState)
}