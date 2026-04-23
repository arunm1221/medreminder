package com.example.medreminder.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val PASSWORD = stringPreferencesKey("password")


}