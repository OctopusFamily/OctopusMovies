package com.octopus.moviesapp.domain.login

import com.octopus.moviesapp.data.repository.account.AccountRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() : String{
        return accountRepository.getRequestToken().toString()
    }
}