package com.heroassistant.repository

import android.content.Context
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getHeavyApps(): List<String> {
        val pm = context.packageManager
        return pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { app ->
                val info = pm.getPackageInfo(app.packageName, PackageManager.GET_ACTIVITIES)
                // Placeholder: ordenar por tamanho estimado
                true
            }
            .take(10)
            .map { it.packageName }
    }
}