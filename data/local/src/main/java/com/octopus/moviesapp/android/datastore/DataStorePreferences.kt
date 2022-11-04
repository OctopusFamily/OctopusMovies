package com.octopus.moviesapp.android.datastore

interface DataStorePreferences {
    fun readBoolean(key: String): Boolean?
    suspend fun writeBoolean(key: String, value: Boolean)
    fun readString(key: String): String?
    suspend fun writeString(key: String, value: String)
}