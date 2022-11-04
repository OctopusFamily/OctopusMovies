package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.android.response.dto.lists.CreatedListsDTO
import com.octopus.moviesapp.domain.model.CreatedList
import javax.inject.Inject

class CreatedListMapper  @Inject constructor() : Mapper<List<CreatedListsDTO>,List<CreatedList>>() {
    override fun map(input: List<CreatedListsDTO>): List<CreatedList> {
        return input.map {
            CreatedList(
                id = it.id ?: 0,
                name = it.name ?: "",
                itemCount = it.itemCount ?: 0
            )
        }
     }
}