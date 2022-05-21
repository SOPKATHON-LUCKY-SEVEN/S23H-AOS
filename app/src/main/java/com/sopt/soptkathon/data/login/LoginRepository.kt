package com.sopt.soptkathon.data.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.soptkathon.data.remote.RetrofitBuilder.customRetrofit
import com.sopt.soptkathon.data.remote.request.RequestUser
import com.sopt.soptkathon.data.remote.response.ResponseLetter
import com.sopt.soptkathon.data.remote.response.ResponseUser
import com.sopt.soptkathon.data.remote.response.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class LoginRepository(private val applicationContext: Context) {
    private val Context.dataStore by preferencesDataStore(name = DATASTORE)
    private val isAutoLoginKey = stringPreferencesKey(USER_ID)
    private val userNameKey = stringPreferencesKey(USER_NAME)

    suspend fun signUp(requestUser: RequestUser): Response<ResponseWrapper<ResponseUser>> {
        return customRetrofit.signUp(requestUser)
    }

    suspend fun getLetterList(userId: String): Response<ResponseWrapper<List<ResponseLetter>>> {
        return customRetrofit.getLetterList(userId)
    }

    fun getUser(): Flow<Pair<String?, String?>?> {
        val user: Flow<Pair<String?, String?>> = applicationContext.dataStore.data.map {
            Pair(it[isAutoLoginKey], it[userNameKey])
        }
        return user
    }

    suspend fun setUser(userId: String, userName: String) {
        applicationContext.dataStore.edit { preferences ->
            preferences[isAutoLoginKey] = userId
            preferences[userNameKey] = userName
        }
    }

    companion object {
        const val DATASTORE = "DATASTORE"
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
    }
}
