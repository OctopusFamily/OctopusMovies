package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseAdapter

class MyListsAdapter(
    items: List<CreatedList>,
    listener: MyListsClicksListener,
) : BaseAdapter<CreatedList>(items, listener) {
    override fun layoutId(): Int = R.layout.item_my_list
    override fun areContentsTheSame(oldItem: CreatedList, newItem: CreatedList): Boolean {
        return oldItem.id == newItem.id
    }
}