package com.octopus.moviesapp.android.usecases.use_case

import com.octopus.moviesapp.data.repository.account.AccountRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() : String{
        return accountRepository.getRequestToken().toString()
    }
}