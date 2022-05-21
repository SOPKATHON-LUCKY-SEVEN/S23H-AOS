package com.sopt.soptkathon.util

import android.app.Activity
import androidx.annotation.ColorInt

fun Activity.setStatusBarColor(@ColorInt color: Int) {
    this.window?.run { statusBarColor = color }
}
