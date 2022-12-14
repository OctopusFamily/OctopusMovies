package com.octopus.moviesapp.android.usecases.account

import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() : String{
        return accountRepository.getRequestToken().toString()
    }
}