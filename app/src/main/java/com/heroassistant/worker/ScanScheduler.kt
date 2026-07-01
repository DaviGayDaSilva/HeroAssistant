package com.heroassistant.worker

import android.content.Context
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ScanScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun schedulePeriodicClean(frequency: String) {
        val interval = when (frequency) {
            "diária" -> 24L
            "semanal" -> 168L
            else -> 24L
        }
        val request = PeriodicWorkRequestBuilder<AutoCleanWorker>(interval, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "auto_clean",
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    fun cancel() {
        WorkManager.getInstance(context).cancelUniqueWork("auto_clean")
    }
}