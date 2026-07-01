package com.heroassistant.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataUsageRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getTodayDataUsage(): String {
        // Placeholder: usar NetworkStatsManager
        return "12 MB"
    }
}