package com.octopus.moviesapp.data.repository.lists

import com.octopus.moviesapp.android.response.AddMovieToListResponse
import com.octopus.moviesapp.android.response.CreateListResponse
import com.octopus.moviesapp.android.response.dto.lists.CreatedListsDTO
import com.octopus.moviesapp.android.response.dto.lists.ListDetailsDTO
import com.octopus.moviesapp.android.service.TMDBApiService
import javax.inject.Inject

class ListsRepositoryImp @Inject constructor(
    private val tmdbApiService: TMDBApiService,
) : ListsRepository {

    override suspend fun createList(sessionId: String, name: String): CreateListResponse {
        return tmdbApiService.createList(sessionId,name)
    }

    override suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedListsDTO> {
        return tmdbApiService.getCreatedLists(accountId,sessionId).items
     }

    override suspend fun getListDetails(listId: Int): List<ListDetailsDTO> {
        return tmdbApiService.getList(listId).items!!
    }

    override suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieToListResponse {
        return tmdbApiService.addMovieToList(listId, sessionId, movieId)
    }
}