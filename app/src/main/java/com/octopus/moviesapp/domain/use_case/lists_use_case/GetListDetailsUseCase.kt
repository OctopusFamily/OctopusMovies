package com.octopus.moviesapp.domain.use_case.lists_use_case

import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.mapper.ListDetailsMapper
import com.octopus.moviesapp.domain.model.ListDetails
import javax.inject.Inject

class GetListDetailsUseCase @Inject constructor(
    private val listsRepository: ListsRepository,
    private val listDetailsMapper: ListDetailsMapper
){
    suspend operator fun invoke(listID : Int): List<ListDetails>{
        val listDetails = listsRepository.getListDetails(listID)
        return listDetails.let { listDetails ->
            listDetailsMapper.map(listDetails)
        } ?: throw Throwable("Unable to get list content")
    }
}