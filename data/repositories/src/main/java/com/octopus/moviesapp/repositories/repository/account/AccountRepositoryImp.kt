package com.octopus.moviesapp.repositories.repository.account

import com.octopus.moviesapp.android.datastore.DataStorePreferences
import com.octopus.moviesapp.android.response.LogoutResponse
import com.octopus.moviesapp.android.response.dto.account.AccountDTO
import com.octopus.moviesapp.android.response.RequestTokenResponse
import com.octopus.moviesapp.android.service.TMDBApiService
import com.octopus.moviesapp.repositories.repository.Constants
import retrofit2.Response
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: TMDBApiService,
    private val dataStorePreferences: DataStorePreferences,
) : AccountRepository {
    override fun getSessionId(): String? {
        return dataStorePreferences.readString(Constants.SESSION_ID_KEY)
    }

    override suspend fun getAccountDetails(sessionId: String): AccountDTO {
        return service.getAccountDetails(sessionId)
    }
    override suspend fun validateRequestTokenWithLogin(body: Map<String, Any>): Response<RequestTokenResponse> {
        return service.validateRequestTokenWithLogin(body)
    }

    override suspend fun logout(sessionId: String): LogoutResponse {
        return service.logout(sessionId)
    }


    override suspend fun getRequestToken(): String? {
        return service.getRequestToken().body()?.requestToken.toString()
    }

    override suspend fun createSessionID(requestToken: String) {
        val response = service.createSession(requestToken).body()
        if (response?.success == true){
            saveSessionId(response.sessionId.toString())
        }
    }

    private suspend fun saveSessionId(sessionId: String) {
        dataStorePreferences.writeString(Constants.SESSION_ID_KEY, sessionId)
    }

}