package com.octopus.moviesapp.domain.mapper

import android.content.Context
import com.octopus.moviesapp.R
import com.octopus.moviesapp.data.remote.response.dto.PersonDTO
import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.getTextOrPlaceholder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PersonDetailsMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : Mapper<PersonDTO, PersonDetails>() {
    override fun map(input: PersonDTO): PersonDetails {
        return PersonDetails(
            name = getTextOrPlaceholder(context, input.personName, R.string.no_name_provided),
            profilePath = buildImageUrl(input.personImageUrl),
            biography = getTextOrPlaceholder(context, input.biography, R.string.no_biography_provided),
            birthday = getTextOrPlaceholder(context, input.birthday, R.string.no_birthday_provided),
            knownForDepartment = getTextOrPlaceholder(context, input.career, R.string.no_birthday_provided),
            popularity = input.popularity ?: 0f,
            placeOfBirth = getTextOrPlaceholder(context, input.placeOfBirth, R.string.no_place_of_birth_provided)
        )
    }
}