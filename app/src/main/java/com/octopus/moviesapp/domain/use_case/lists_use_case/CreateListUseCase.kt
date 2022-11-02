package com.octopus.moviesapp.domain.use_case.lists_use_case

import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.model.CreatedList
import javax.inject.Inject

class CreateListUseCase @Inject constructor(
    private val listsRepository: ListsRepository,
    private val accountRepository: AccountRepository,
    private val getCreatedListsUseCase: GetCreatedListsUseCase
) {
    suspend operator fun invoke(listName: String): List<CreatedList> {
        val sessionID = accountRepository.getSessionId().toString()
        return sessionID.let {
            val response = listsRepository.createList(sessionID, listName)
            if (response.success == true) {
                getCreatedListsUseCase()
            } else {
                throw Throwable("Unable to create list")
            }
        }
    }
}