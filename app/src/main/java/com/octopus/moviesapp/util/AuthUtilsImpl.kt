package com.octopus.moviesapp.util

import android.content.Context
import androidx.core.text.isDigitsOnly
import com.octopus.moviesapp.R
import com.octopus.moviesapp.util.extensions.isEnglishLettersOnly
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthUtilsImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ) : AuthUtils {
    override fun validateUsername(username: String?): InputValidationResult {
        return when {
            username.isNullOrEmpty() -> {
                InputValidationResult.InValid(context.getString(R.string.username_is_empty_error))
            }
            username.length < 3 || username.length > 30 -> {
                InputValidationResult.InValid(context.getString(R.string.username_length_error))
            }
            username.isDigitsOnly() -> {
                InputValidationResult.InValid(context.getString(R.string.username_is_digits_only_error))
            }
            username.contains(" ") -> {
                InputValidationResult.InValid(context.getString(R.string.username_has_whitespace_error))
            }
            else -> InputValidationResult.Valid
        }
    }

    override fun validatePassword(password: String?): InputValidationResult {
        return when {
            password.isNullOrEmpty() -> {
                InputValidationResult.InValid(context.getString(R.string.password_is_empty_error))
            }
            password.length < 8 || password.length > 16 -> {
                InputValidationResult.InValid(context.getString(R.string.password_length_error))
            }
            password.contains(" ") -> {
                InputValidationResult.InValid(context.getString(R.string.password_has_whitespace_error))
            }
            password.isDigitsOnly() -> {
                InputValidationResult.InValid(context.getString(R.string.password_is_digits_only_error))
            }
            password.isEnglishLettersOnly() -> {
                InputValidationResult.InValid(context.getString(R.string.password_is_letters_only_error))
            }
            else -> InputValidationResult.Valid
        }
    }
}