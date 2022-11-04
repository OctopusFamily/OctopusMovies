package com.octopus.moviesapp.repositories.repository.account

import com.octopus.moviesapp.android.response.LogoutResponse
import com.octopus.moviesapp.android.response.dto.account.AccountDTO
import com.octopus.moviesapp.android.response.RequestTokenResponse
import retrofit2.Response

interface AccountRepository {
    suspend fun getAccountDetails(sessionId: String): AccountDTO

    suspend fun logout(sessionId: String): LogoutResponse

    fun getSessionId() :String?

    suspend fun getRequestToken() : String?

    suspend fun validateRequestTokenWithLogin(body : Map<String,Any>) : Response<RequestTokenResponse>

    suspend fun createSessionID(requestToken: String)
}

