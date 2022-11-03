package com.octopus.moviesapp.domain.login

import com.octopus.moviesapp.data.repository.account.AccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return accountRepository.login(username, password)
    }
}