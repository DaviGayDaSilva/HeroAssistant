package com.heroassistant.util

import android.content.Context
import moe.shizuku.api.ShizukuProvider
import javax.inject.Inject

class ShizukuChecker @Inject constructor(private val context: Context) {
    fun isShizukuAvailable(): Boolean {
        return try {
            ShizukuProvider.isShizukuAvailable(context)
        } catch (e: Exception) {
            false
        }
    }
}