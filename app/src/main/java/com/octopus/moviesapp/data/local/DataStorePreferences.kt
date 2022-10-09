package com.octopus.moviesapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferences(
    context: Context
) : DataStorePref {
    private val prefDataStore = context.preferencesDataStore

    override fun readBoolean(key: String): Flow<Boolean?> {
        return prefDataStore.data.map { preference ->
            preference[booleanPreferencesKey(key)]
        }
    }

    override suspend fun writeBoolean(key: String, value: Boolean) {
        prefDataStore.edit { MutableBooleanPref ->
            MutableBooleanPref[booleanPreferencesKey(key)] = value
        }
    }

    override fun readString(key: String): Flow<String?> {
        return prefDataStore.data.map { preference ->
            preference[stringPreferencesKey(key)]
        }
    }

    override suspend fun writeString(key: String, value: String) {
        prefDataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(key)] = value
        }
    }

    companion object {
        private val Context.preferencesDataStore: DataStore<Preferences>
                by preferencesDataStore("octopusMovies")
    }
}