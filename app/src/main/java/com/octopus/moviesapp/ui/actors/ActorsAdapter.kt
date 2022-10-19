package com.octopus.moviesapp.ui.actors

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Person
import com.octopus.moviesapp.ui.base.BaseAdapter

class ActorsAdapter(
    person: List<Person>,
    listener: ActorsClicksListener
) : BaseAdapter<Person>(person, listener) {
    override fun layoutId(): Int = R.layout.item_nested_actor_info
}