package com.octopus.moviesapp.domain.types


enum class Language(val languageCode: String) {
    ENGLISH("en"),
    ARABIC("ar");

    companion object {
        fun fromLanguageCode(languageCode: String): com.octopus.moviesapp.android.local.types.Language {
            return values().associateBy(com.octopus.moviesapp.android.local.types.Language::languageCode)[languageCode] ?: throw IllegalArgumentException("No enum constant!")
        }
    }
}