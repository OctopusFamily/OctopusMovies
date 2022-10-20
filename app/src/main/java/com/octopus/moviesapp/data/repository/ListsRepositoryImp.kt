package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.response.lists.CreateListResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import javax.inject.Inject

class ListsRepositoryImp @Inject constructor(
    private val tmdbApiService: TMDBApiService
    ) : ListsRepository {

    override suspend fun createList(sessionId: String, name: String): CreateListResponse {
        return tmdbApiService.createList(sessionId,name)
    }
}