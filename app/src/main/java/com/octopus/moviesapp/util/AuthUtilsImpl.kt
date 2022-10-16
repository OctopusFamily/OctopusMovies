package com.octopus.moviesapp.util

import android.content.Context
import androidx.core.text.isDigitsOnly
import com.octopus.moviesapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthUtilsImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ) : AuthUtils {
    override fun validateUsername(username: String?): String? {
        return when {
            username.isNullOrEmpty() -> {
                context.getString(R.string.username_is_empty_error) }
            username.length < 3 || username.length > 30 -> {
                context.getString(R.string.username_length_error)
            }
            username.isDigitsOnly() -> {
                context.getString(R.string.username_is_digits_only_error)
            }
            username.contains(" ") -> {
                context.getString(R.string.username_has_whitespace_error)
            }
            else -> null
        }
    }

    override fun validatePassword(password: String?): String? {
        return when {
            password.isNullOrEmpty() -> {
                context.getString(R.string.password_is_empty_error)
            }
            password.length < 8 || password.length > 16 -> {
                context.getString(R.string.password_length_error)
            }
            password.contains(" ") -> {
                context.getString(R.string.password_has_whitespace_error)
            }
            !password.isEnglishLettersAndDigitsOnly() -> {
                context.getString(R.string.password_is_not_valid_error)
            }
            password.isDigitsOnly() -> {
                context.getString(R.string.password_is_digits_only_error)
            }
            password.isEnglishLettersOnly() -> {
                context.getString(R.string.password_is_letters_only_error)
            }
            else -> null
        }
    }
}