package com.octopus.moviesapp.repositories.repository.lists

import com.octopus.moviesapp.android.response.AddMovieToListResponse
import com.octopus.moviesapp.android.response.CreateListResponse
import com.octopus.moviesapp.android.response.dto.lists.CreatedListsDTO
import com.octopus.moviesapp.android.response.dto.lists.ListDetailsDTO

interface ListsRepository {
    suspend fun createList(sessionId: String, name: String): CreateListResponse
    suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedListsDTO>
    suspend fun getListDetails(listId: Int): List<ListDetailsDTO>
    suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieToListResponse
}