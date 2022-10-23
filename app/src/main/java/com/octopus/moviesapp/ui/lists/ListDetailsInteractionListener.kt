package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.ui.base.BaseInteractionListener

interface ListDetailsInteractionListener : BaseInteractionListener {
    fun onItemClick(item: ListDetails)

}