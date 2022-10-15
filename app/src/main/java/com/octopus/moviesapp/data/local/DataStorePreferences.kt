package com.octopus.moviesapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStorePref {
    override fun readBoolean(key: String): Flow<Boolean?> {
        return dataStore.data.map { preference ->
            preference[booleanPreferencesKey(key)]
        }
    }

    override suspend fun writeBoolean(key: String, value: Boolean) {
        dataStore.edit { MutableBooleanPref ->
            MutableBooleanPref[booleanPreferencesKey(key)] = value
        }
    }

    override fun readString(key: String): Flow<String?> {
        return dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)]
        }
    }

    override suspend fun writeString(key: String, value: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(key)] = value
        }
    }
}