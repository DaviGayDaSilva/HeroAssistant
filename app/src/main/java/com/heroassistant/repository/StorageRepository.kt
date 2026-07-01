package com.heroassistant.repository

import android.content.Context
import android.os.StatFs
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StorageRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getAvailableStorage(): String {
        val stat = StatFs(context.filesDir.absolutePath)
        val availableBytes = stat.availableBytes
        val gb = availableBytes / (1024.0 * 1024.0 * 1024.0)
        return "%.1f GB".format(gb)
    }
}