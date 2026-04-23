package com.example.medreminder.data.local.datastore

import android.R
import android.content.Context
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class AppDataStore(private val context: Context) {

    val usernameFlow: Flow<String> =
        context.dataStore.data
            .catch {
                exception->
                if (exception is IOException)
                    emit(emptyPreferences())
                else throw exception
            }.map {
                prefs->
                prefs[PreferenceKeys.USER_NAME]?:""
            }

    val passWordFlow : Flow<String> =
        context.dataStore.data
            .catch { exception->
                if (exception is IOException)
                    emit(emptyPreferences())
                else throw  exception
            }.map {
                it[PreferenceKeys.PASSWORD]?:""
            }

    suspend fun writeUserName(name: String){
        context.dataStore.edit { it->
            it[PreferenceKeys.USER_NAME]= name
        }
    }

    suspend fun writePassWord(passWord: String){
        context.dataStore.edit { it[PreferenceKeys.PASSWORD]=passWord }
    }

    //onboard

    val isOnBoardSeen: Flow<Boolean> =
        context.dataStore.data
            .catch { exception->
                if (exception is IOException)
                    emit(emptyPreferences())
                else
                    throw exception
            }.map {
                it[PreferenceKeys.ONBOARD_SEEN]?:false
            }

    suspend fun setOnboardSeen(){
        context.dataStore.edit {
            it->
            it[PreferenceKeys.ONBOARD_SEEN]=true
        }
    }

}