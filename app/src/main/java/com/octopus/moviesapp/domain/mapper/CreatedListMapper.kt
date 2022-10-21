package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.lists.CreatedListsDto
import com.octopus.moviesapp.domain.model.CreatedList
import javax.inject.Inject

class CreatedListMapper  @Inject constructor() : Mapper<List<CreatedListsDto>,List<CreatedList>>() {
    override fun map(input: List<CreatedListsDto>): List<CreatedList> {
        return input.map {
            CreatedList(
                id = it.id ?: 0,
                name = it.name ?: "",
                itemCount = it.itemCount ?: 0
            )
        }
     }
}