package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.remote.response.login.RequestTokenResponse
import com.octopus.moviesapp.domain.model.Account
import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AccountRepository {
    fun getSessionId() : String?

    suspend fun getRequestToken() : String?

    suspend fun validateRequestTokenWithLogin(body : Map<String,Any>) : Response<RequestTokenResponse>

    suspend fun createSessionID(requestToken: String)

}

