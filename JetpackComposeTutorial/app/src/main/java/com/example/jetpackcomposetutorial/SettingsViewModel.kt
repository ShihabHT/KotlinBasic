package com.example.jetpackcomposetutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(private val dataStore: DataStore<Preferences>) : ViewModel() {

    // Define the key and default value in the ViewModel
    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private val NOTIFICATION_ENABLED_KEY = booleanPreferencesKey("notification_enabled")
    private val ACCEPT_TERMS_KEY = booleanPreferencesKey("accept_terms")
    private val GENDER_KEY = stringPreferencesKey("gender")
    private val SLIDER_VALUE_KEY = floatPreferencesKey("slider_value")

    private val DEFAULT_USER_NAME = "Android"
    private val DEFAULT_NOTIFICATION_ENABLED = true
    private val DEFAULT_ACCEPT_TERMS = true
    private val DEFAULT_GENDER = "Undisclosed"
    private val DEFAULT_SLIDER_VALUE = 0f

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

    // Expose acceptTerms key Flow from DataStore
    val acceptTermsFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[ACCEPT_TERMS_KEY] ?: DEFAULT_ACCEPT_TERMS
    }
    // a suspend function to save the acceptTerms
    fun saveAcceptTerms(accepted: Boolean) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[ACCEPT_TERMS_KEY] = accepted
            }
        }

    }

    // Expose gender key Flow from DataStore
    val genderFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[GENDER_KEY] ?: DEFAULT_GENDER
    }
    // a suspend function to save the gender
    fun saveGender(gender: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[GENDER_KEY] = gender
            }
        }
    }

    // Expose sliderValue key Flow from DataStore
    val sliderValueFlow: Flow<Float> = dataStore.data.map { preferences ->
        preferences[SLIDER_VALUE_KEY] ?: DEFAULT_SLIDER_VALUE
    }
    // a suspend function to save the sliderValue
    fun saveSliderValue(value: Float) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[SLIDER_VALUE_KEY] = value
            }
        }
    }


}