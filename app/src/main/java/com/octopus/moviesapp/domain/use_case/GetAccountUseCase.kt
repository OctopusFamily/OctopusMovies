package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.domain.mapper.AccountMapper
import com.octopus.moviesapp.domain.model.Account
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
) {
    suspend operator fun invoke(sessionId: String): Account {
        return accountMapper.map(accountRepository.getAccountDetails(sessionId))
    }
}