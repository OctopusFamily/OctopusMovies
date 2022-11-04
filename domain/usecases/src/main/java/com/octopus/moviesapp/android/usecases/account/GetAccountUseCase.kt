package com.octopus.moviesapp.android.usecases.account

import com.octopus.moviesapp.models.model.Account
import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountMapper: com.octopus.moviesapp.android.mapper.AccountMapper
) {
    suspend operator fun invoke(sessionId: String): Account {
        return accountMapper.map(accountRepository.getAccountDetails(sessionId))
    }
}