package com.octopus.moviesapp.data.repository.lists

import com.octopus.moviesapp.data.remote.response.lists.AddMovieToListResponse
import com.octopus.moviesapp.data.remote.response.lists.CreateListResponse
import com.octopus.moviesapp.data.remote.response.lists.CreatedListsDto
import com.octopus.moviesapp.data.remote.response.lists.ListDetailsDto

interface ListsRepository {
    suspend fun createList(sessionId: String, name: String): CreateListResponse
    suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedListsDto>
    suspend fun getListDetails(listId: Int): List<ListDetailsDto>
    suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieToListResponse
}