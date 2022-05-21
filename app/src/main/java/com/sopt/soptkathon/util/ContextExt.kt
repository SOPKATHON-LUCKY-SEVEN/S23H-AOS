package com.sopt.soptkathon.util

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.colorOf(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)
