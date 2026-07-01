package com.heroassistant.util

import java.io.File
import javax.inject.Inject

class FileUtils @Inject constructor() {
    fun deleteRecursively(file: File): Boolean {
        return file.deleteRecursively()
    }

    fun getFormattedSize(bytes: Long): String {
        val kb = bytes / 1024.0
        val mb = kb / 1024.0
        val gb = mb / 1024.0
        return when {
            gb >= 1 -> "%.2f GB".format(gb)
            mb >= 1 -> "%.2f MB".format(mb)
            kb >= 1 -> "%.2f KB".format(kb)
            else -> "$bytes B"
        }
    }
}