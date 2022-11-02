package com.octopus.moviesapp.data.repository.lists

import com.octopus.moviesapp.data.remote.response.lists.AddMovieToListResponse
import com.octopus.moviesapp.data.remote.response.lists.CreateListResponse
import com.octopus.moviesapp.data.remote.response.lists.CreatedListsDto
import com.octopus.moviesapp.data.remote.response.lists.ListDetailsDto
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import javax.inject.Inject

class ListsRepositoryImp @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : ListsRepository {

    override suspend fun createList(sessionId: String, name: String): CreateListResponse {
        return tmdbApiService.createList(sessionId,name)
    }

    override suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedListsDto> {
        return tmdbApiService.getCreatedLists(accountId,sessionId).items
     }

    override suspend fun getListDetails(listId: Int): List<ListDetailsDto> {
        return tmdbApiService.getList(listId).items!!
    }

    override suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieToListResponse {
        return tmdbApiService.addMovieToList(listId, sessionId, movieId)
    }
}