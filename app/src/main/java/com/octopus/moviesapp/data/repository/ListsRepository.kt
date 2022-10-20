package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.response.lists.CreateListResponse

interface ListsRepository {
    suspend fun createList(sessionId: String, name: String): CreateListResponse
}