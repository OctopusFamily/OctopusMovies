package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.response.lists.CreateListResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.CreatedListMapper
import com.octopus.moviesapp.domain.model.CreatedList
import javax.inject.Inject

class ListsRepositoryImp @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val createdListMapper: CreatedListMapper
    ) : ListsRepository {

    override suspend fun createList(sessionId: String, name: String): CreateListResponse {
        return tmdbApiService.createList(sessionId,name)
    }

    override suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedList> {
        return createdListMapper.map(tmdbApiService.getCreatedLists(accountId , sessionId).items)
     }
}