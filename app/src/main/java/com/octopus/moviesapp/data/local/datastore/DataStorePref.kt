package com.octopus.moviesapp.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePref {
    fun readBoolean(key: String): Flow<Boolean?>
    suspend fun writeBoolean(key: String, value: Boolean)
    fun readString(key: String): Flow<String?>
    suspend fun writeString(key: String, value: String)
}