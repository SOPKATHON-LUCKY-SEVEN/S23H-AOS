package com.sopt.soptkathon

import android.app.Application
import com.sopt.soptkathon.data.login.LoginRepository

class MainApp : Application() {
    val repository by lazy { LoginRepository(applicationContext) }
}