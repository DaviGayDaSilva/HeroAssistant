package com.heroassistant.service

import android.content.Context
import javax.inject.Inject

class PrivilegedActor @Inject constructor(
    private val context: Context,
    private val shizukuUserService: ShizukuUserService
) : DeviceActor {

    override suspend fun cleanCache(packageName: String): Boolean {
        return shizukuUserService.exec("pm clear $packageName").contains("Success")
    }

    override suspend fun uninstallApp(packageName: String): Boolean {
        return shizukuUserService.exec("pm uninstall --user 0 $packageName").contains("Success")
    }

    override suspend fun disableApp(packageName: String): Boolean {
        return shizukuUserService.exec("pm disable-user --user 0 $packageName").contains("Success")
    }

    override suspend fun getBatteryStats(): Map<String, Any> {
        val output = shizukuUserService.exec("dumpsys batterystats --checkin")
        // Parse mínimo
        val level = output.lines().firstOrNull { it.startsWith("9,0,i,level,") }
            ?.split(",")?.getOrNull(4) ?: "0"
        return mapOf("level" to level.trim().toIntOrNull()?.let { it } ?: 0)
    }

    override suspend fun getStorageStats(): Map<String, Long> {
        val totalOut = shizukuUserService.exec("stat -f -c '%S * %b' /data")
        val availOut = shizukuUserService.exec("stat -f -c '%S * %f' /data")
        // Simples conversão
        return mapOf(
            "total" to (totalOut.trim().toLongOrNull() ?: 0L),
            "available" to (availOut.trim().toLongOrNull() ?: 0L)
        )
    }

    override suspend fun scanLargeFiles(minSizeMB: Int): List<String> {
        val output = shizukuUserService.exec(
            "find /sdcard -type f -size +${minSizeMB}M 2>/dev/null"
        )
        return output.lines().filter { it.isNotBlank() }
    }

    override suspend fun scanDuplicates(): List<String> {
        // Algoritmo simplificado usando md5sum via shell
        val output = shizukuUserService.exec(
            "find /sdcard -type f -exec md5sum {} + 2>/dev/null | sort | uniq -w32 -d --all-repeated=separate"
        )
        return output.lines().filter { it.isNotBlank() }
    }

    override suspend fun trimAllCaches(): Boolean {
        return shizukuUserService.exec("pm trim-caches 10G").isNotEmpty()
    }
}