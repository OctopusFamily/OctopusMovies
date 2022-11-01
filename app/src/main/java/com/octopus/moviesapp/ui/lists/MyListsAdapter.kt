package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState

class MyListsAdapter(
    items: List<CreatedListsUIState>,
    listener: MyListsClicksListener,
) : BaseAdapter<CreatedListsUIState>(items, listener) {
    override fun layoutId(): Int = R.layout.item_my_list
    override fun areContentsTheSame(oldItem: CreatedListsUIState, newItem: CreatedListsUIState): Boolean {
        return oldItem.listID == newItem.listID
    }
}