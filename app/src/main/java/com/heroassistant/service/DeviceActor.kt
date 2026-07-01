package com.heroassistant.service

interface DeviceActor {
    suspend fun cleanCache(packageName: String): Boolean
    suspend fun uninstallApp(packageName: String): Boolean
    suspend fun disableApp(packageName: String): Boolean
    suspend fun getBatteryStats(): Map<String, Any>
    suspend fun getStorageStats(): Map<String, Long>
    suspend fun scanLargeFiles(minSizeMB: Int): List<String>
    suspend fun scanDuplicates(): List<String>
    suspend fun trimAllCaches(): Boolean
}