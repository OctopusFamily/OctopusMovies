package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.base.BaseInteractionListener


class CreatedListAdapter(items: List<CreatedList>, listener: CreatedListInteractionListener) :
    BaseAdapter<CreatedList>(items, listener) {
     override fun layoutId(): Int = R.layout.item_all_list
    override fun areContentsTheSame(oldItem: CreatedList, newItem: CreatedList): Boolean {
        return oldItem.id == newItem.id
    }
}

interface CreatedListInteractionListener : BaseInteractionListener {
    fun onListClick(item: CreatedList)
}