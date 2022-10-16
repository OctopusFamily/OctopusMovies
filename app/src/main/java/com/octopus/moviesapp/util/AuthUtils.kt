package com.octopus.moviesapp.util

interface AuthUtils {
    fun validateUsername(username: String?): String?
    fun validatePassword(password: String?): String?
}