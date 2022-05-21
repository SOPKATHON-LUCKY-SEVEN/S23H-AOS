package com.sopt.soptkathon.data.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.soptkathon.data.remote.RetrofitBuilder.customRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginRepository(private val applicationContext: Context) {
    private val Context.dataStore by preferencesDataStore(name = DATASTORE)
    private val isAutoLoginKey = intPreferencesKey(IS_AUTO_LOGIN)

    fun isAutoLogin(): Flow<Int> {
        val isAutoLogin: Flow<Int> = applicationContext.dataStore.data.map {
            it[isAutoLoginKey] ?: -1
        }
        return isAutoLogin
    }

    suspend fun setAutoLogin(isAutoLogin: Int) {
        applicationContext.dataStore.edit { preferences ->
            preferences[isAutoLoginKey] = isAutoLogin
        }
    }

    companion object {
        const val DATASTORE = "DATASTORE"
        const val IS_AUTO_LOGIN = "IS_AUTO_LOGIN"
    }
}