package com.sopt.soptkathon

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import com.sopt.soptkathon.data.login.LoginRepository

class MainApp : Application() {
    val repository by lazy { LoginRepository(applicationContext) }
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }
}