package com.octopus.moviesapp.data.repository.lists

import com.octopus.moviesapp.data.remote.response.lists.CreateListResponse
import com.octopus.moviesapp.domain.model.CreatedList

interface ListsRepository {
    suspend fun createList(sessionId: String, name: String): CreateListResponse
    suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedList>

}