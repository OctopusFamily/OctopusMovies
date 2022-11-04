package com.octopus.moviesapp.repositories.repository.search

import com.octopus.moviesapp.android.response.dto.SearchDTO

interface SearchRepository {
    suspend fun getSearchMultiMedia(query: String): List<SearchDTO>
}