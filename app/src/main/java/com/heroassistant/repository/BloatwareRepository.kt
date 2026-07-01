package com.heroassistant.repository

import android.content.Context
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BloatwareRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun detectBloatware(): List<String> {
        val pm = context.packageManager
        val installed = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        // Lista estática de bloatware conhecido (exemplo)
        val knownBloat = listOf(
            "com.facebook.katana",
            "com.microsoft.skydrive",
            "com.samsung.android.bixby.agent"
        )
        return installed.filter { it.packageName in knownBloat }.map { it.packageName }
    }
}