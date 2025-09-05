package com.example.jetpackcomposetutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    // Define the key and default value in the ViewModel
    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private val NOTIFICATION_ENABLED_KEY = booleanPreferencesKey("notification_enabled")

    private val DEFAULT_USER_NAME = "Android"
    private val DEFAULT_NOTIFICATION_ENABLED = true

    // Expose the userName key Flow from DataStore
    val userNameFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: DEFAULT_USER_NAME
    }
    // A suspend function to save the userName
    fun saveUserName(name: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[USER_NAME_KEY] = name
            }
        }
    }

    // Expose notificationEnabled key Flow from DataStore
    val notificationEnabledFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[NOTIFICATION_ENABLED_KEY] ?: DEFAULT_NOTIFICATION_ENABLED
    }
    // a suspend function to save the notificationEnabled
    fun saveNotificationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[NOTIFICATION_ENABLED_KEY] = enabled
            }
        }
    }

}