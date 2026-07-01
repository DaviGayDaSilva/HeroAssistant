package com.heroassistant.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.io.File

class FileUtilsTest {

    private val fileUtils = FileUtils()

    @Test
    fun `deleteRecursively removes directory`() {
        val dir = File("test_dir")
        dir.mkdirs()
        File(dir, "file.txt").createNewFile()
        val result = fileUtils.deleteRecursively(dir)
        assertThat(result).isTrue()
        assertThat(dir.exists()).isFalse()
    }

    @Test
    fun `getFormattedSize returns bytes`() {
        val size = fileUtils.getFormattedSize(500L)
        assertThat(size).isEqualTo("500 B")
    }

    @Test
    fun `getFormattedSize returns KB`() {
        val size = fileUtils.getFormattedSize(2048L)
        assertThat(size).isEqualTo("2.00 KB")
    }

    @Test
    fun `getFormattedSize returns MB`() {
        val size = fileUtils.getFormattedSize(3_145_728L)
        assertThat(size).isEqualTo("3.00 MB")
    }

    @Test
    fun `getFormattedSize returns GB`() {
        val size = fileUtils.getFormattedSize(2_147_483_648L)
        assertThat(size).isEqualTo("2.00 GB")
    }

    @Test
    fun `deleteRecursively returns false for non-existent file`() {
        val dir = File("non_existent_dir")
        val result = fileUtils.deleteRecursively(dir)
        assertThat(result).isFalse()
    }
}