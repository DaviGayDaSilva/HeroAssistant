package com.heroassistant.service

import android.content.Context
import android.os.IBinder
import moe.shizuku.api.ShizukuClient
import moe.shizuku.api.ShizukuClientV3
import moe.shizuku.api.SystemServiceConnector
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ShizukuUserService @Inject constructor(
    private val context: Context
) {
    suspend fun exec(command: String): String {
        return suspendCoroutine { continuation ->
            try {
                val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
                val result = process.inputStream.bufferedReader().readText()
                process.waitFor()
                continuation.resume(result)
            } catch (e: Exception) {
                continuation.resume("")
            }
        }
    }
}