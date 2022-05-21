package com.sopt.soptkathon.data.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.soptkathon.data.remote.RetrofitBuilder.customRetrofit
import com.sopt.soptkathon.data.remote.request.RequestUser
import com.sopt.soptkathon.data.remote.response.ResponseLetterList
import com.sopt.soptkathon.data.remote.response.ResponseUser
import com.sopt.soptkathon.data.remote.response.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class LoginRepository(private val applicationContext: Context) {
    private val Context.dataStore by preferencesDataStore(name = DATASTORE)
    private val isAutoLoginKey = stringPreferencesKey(IS_AUTO_LOGIN)

    suspend fun signUp(requestUser: RequestUser): Response<ResponseWrapper<ResponseUser>> {
        return customRetrofit.signUp(requestUser)
    }

    suspend fun getLetterList(userId: String): Response<ResponseWrapper<List<ResponseLetterList>>> {
        return customRetrofit.getLetterList(userId)
    }

    fun getUserId(): Flow<String?> {
        val isAutoLogin: Flow<String?> = applicationContext.dataStore.data.map {
            it[isAutoLoginKey]
        }
        return isAutoLogin
    }

    suspend fun setAutoLogin(isAutoLogin: String) {
        applicationContext.dataStore.edit { preferences ->
            preferences[isAutoLoginKey] = isAutoLogin
        }
    }

    companion object {
        const val DATASTORE = "DATASTORE"
        const val IS_AUTO_LOGIN = "IS_AUTO_LOGIN"
    }
}
