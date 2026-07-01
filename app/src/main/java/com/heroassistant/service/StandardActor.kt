package com.heroassistant.service

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StatFs
import android.os.storage.StorageManager
import android.provider.Settings
import androidx.core.content.getSystemService
import java.io.File
import javax.inject.Inject

class StandardActor @Inject constructor(
    private val context: Context
) : DeviceActor {

    override suspend fun cleanCache(packageName: String): Boolean {
        return try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun uninstallApp(packageName: String): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_DELETE)
            intent.data = Uri.parse("package:$packageName")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun disableApp(packageName: String): Boolean {
        return try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getBatteryStats(): Map<String, Any> {
        // Placeholder: usar BatteryManager para obter nível e saúde
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as android.os.BatteryManager
        val level = batteryManager.getIntProperty(android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return mapOf("level" to level)
    }

    override suspend fun getStorageStats(): Map<String, Long> {
        val stat = StatFs(context.filesDir.absolutePath)
        val total = stat.totalBytes
        val available = stat.availableBytes
        return mapOf("total" to total, "available" to available)
    }

    override suspend fun scanLargeFiles(minSizeMB: Int): List<String> {
        // Escaneia apenas diretórios acessíveis sem Shizuku
        val root = context.getExternalFilesDir(null) ?: return emptyList()
        return findLargeFiles(root, minSizeMB * 1024 * 1024L)
    }

    override suspend fun scanDuplicates(): List<String> {
        // Implementação simplificada
        return emptyList()
    }

    override suspend fun trimAllCaches(): Boolean {
        context.cacheDir.deleteRecursively()
        context.externalCacheDir?.deleteRecursively()
        return true
    }

    private fun findLargeFiles(dir: File, minSize: Long): List<String> {
        val result = mutableListOf<String>()
        dir.walkTopDown().forEach { file ->
            if (file.isFile && file.length() >= minSize) {
                result.add(file.absolutePath)
            }
        }
        return result
    }
}