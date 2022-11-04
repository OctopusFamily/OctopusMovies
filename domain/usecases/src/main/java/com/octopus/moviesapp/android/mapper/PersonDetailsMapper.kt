package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.PersonDTO
import com.octopus.moviesapp.models.model.PersonDetails
import javax.inject.Inject

class PersonDetailsMapper @Inject constructor(
) : Mapper<PersonDTO, PersonDetails>() {
    override fun map(input: PersonDTO): PersonDetails {
        return PersonDetails(
            name =  input.personName ?: "",
            profilePath = buildImageUrl(input.personImageUrl),
            biography =  input.biography ?: "",
            birthday =  input.birthday ?: "",
            knownForDepartment = input.career ?:"",
            popularity = input.popularity ?: 0f,
            placeOfBirth = input.placeOfBirth ?: ""
        )
    }
}