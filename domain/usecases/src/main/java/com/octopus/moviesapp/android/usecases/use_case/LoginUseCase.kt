package com.octopus.moviesapp.android.usecases.use_case

import com.octopus.moviesapp.android.remote.response.login.ErrorResponse
import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val jsonParser: JsonParser,
) {
    suspend operator fun invoke(username: String, password: String): LoginResponse {
        val token = getRequestTokenUseCase()
        val body = mapOf(
            "username" to username,
            "password" to password,
            "request_token" to token,
        ).toMap()

        val validateRequestTokenWithLogin = accountRepository.validateRequestTokenWithLogin(body)
       return if (validateRequestTokenWithLogin.isSuccessful) {
          validateRequestTokenWithLogin.body()?.requestToken?.let { requestToken ->
              accountRepository.createSessionID(requestToken) }
           LoginResponse.Success
        }
        else{
            val errorResponse = jsonParser.parseFromJson(validateRequestTokenWithLogin.errorBody()?.string(), ErrorResponse::class.java)
           LoginResponse.Failure(errorResponse.statusMessage.toString())
        }
    }
}