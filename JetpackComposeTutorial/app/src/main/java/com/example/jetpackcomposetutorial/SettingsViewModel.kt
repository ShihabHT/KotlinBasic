package com.example.jetpackcomposetutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    // Define the key and default value in the ViewModel
    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private val DEFAULT_USER_NAME = "Android"

    // Expose the Flow from DataStore
    val userNameFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: DEFAULT_USER_NAME
    }

    // A suspend function to save the data
    fun saveUserName(name: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[USER_NAME_KEY] = name
            }
        }
    }
}