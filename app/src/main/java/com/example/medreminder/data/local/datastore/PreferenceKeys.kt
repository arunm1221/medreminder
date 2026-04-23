package com.example.medreminder.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val PASSWORD = stringPreferencesKey("password")

    val ONBOARD_SEEN  = booleanPreferencesKey("onboard_seen")


}