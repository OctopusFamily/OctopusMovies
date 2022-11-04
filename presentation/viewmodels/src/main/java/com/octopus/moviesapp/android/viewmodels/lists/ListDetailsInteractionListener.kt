package com.octopus.moviesapp.android.viewmodels.lists

import com.octopus.moviesapp.ui.base.BaseInteractionListener
import com.octopus.moviesapp.ui.lists.listsUIState.ListDetailsUIState

interface ListDetailsInteractionListener : BaseInteractionListener {
    fun onItemClick(item: ListDetailsUIState)
}