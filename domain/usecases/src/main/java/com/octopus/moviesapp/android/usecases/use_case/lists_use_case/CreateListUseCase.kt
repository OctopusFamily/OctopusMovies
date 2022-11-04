package com.octopus.moviesapp.android.usecases.use_case.lists_use_case


import com.octopus.moviesapp.models.model.CreatedList
import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import com.octopus.moviesapp.repositories.repository.lists.ListsRepository
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