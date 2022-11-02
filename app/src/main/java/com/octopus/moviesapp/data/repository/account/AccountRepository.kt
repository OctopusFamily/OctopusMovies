package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.remote.response.LogoutResponse
import com.octopus.moviesapp.data.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.data.remote.response.login.RequestTokenResponse
import retrofit2.Response

interface AccountRepository {
    suspend fun getAccountDetails(sessionId: String): AccountDTO

    suspend fun logout(sessionId: String): LogoutResponse

    fun getSessionId() :String?

    suspend fun getRequestToken() : String?

    suspend fun validateRequestTokenWithLogin(body : Map<String,Any>) : Response<RequestTokenResponse>

    suspend fun createSessionID(requestToken: String)
}

