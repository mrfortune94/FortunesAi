package com.fortunateworld.grokxxx.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "grok_settings")

object PreferenceKeys {
    val GROK_API_KEY = stringPreferencesKey("grok_api_key")
    val UNFILTERED_MODE = booleanPreferencesKey("unfiltered_mode")
    val CHAT_MODEL = stringPreferencesKey("chat_model")
    val IMAGE_MODEL = stringPreferencesKey("image_model")
}

class AppPreferences(private val context: Context) {
    val apiKeyFlow: Flow<String> = context.dataStore.data.map { it[PreferenceKeys.GROK_API_KEY] ?: "" }
    val unfilteredFlow: Flow<Boolean> = context.dataStore.data.map { it[PreferenceKeys.UNFILTERED_MODE] ?: true }
    val chatModelFlow: Flow<String> = context.dataStore.data.map { it[PreferenceKeys.CHAT_MODEL] ?: "grok-4" }
    val imageModelFlow: Flow<String> = context.dataStore.data.map { it[PreferenceKeys.IMAGE_MODEL] ?: "grok-2-image-1212" }

    suspend fun saveApiKey(key: String) = context.dataStore.edit { it[PreferenceKeys.GROK_API_KEY] = key }
    suspend fun saveUnfiltered(enabled: Boolean) = context.dataStore.edit { it[PreferenceKeys.UNFILTERED_MODE] = enabled }
    suspend fun saveChatModel(model: String) = context.dataStore.edit { it[PreferenceKeys.CHAT_MODEL] = model }
    suspend fun saveImageModel(model: String) = context.dataStore.edit { it[PreferenceKeys.IMAGE_MODEL] = model }
}
