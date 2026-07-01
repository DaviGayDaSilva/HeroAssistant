package com.heroassistant.repository

import android.content.Context
import android.os.StatFs
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class StorageRepositoryTest {

    private lateinit var context: Context
    private lateinit var storageRepository: StorageRepository

    @Before
    fun setUp() {
        context = mockk(relaxed = true)
        every { context.filesDir } returns mockk(relaxed = true)
        mockkStatic(StatFs::class)
        val statFs = mockk<StatFs>(relaxed = true)
        every { StatFs(any<String>()) } returns statFs
        every { statFs.availableBytes } returns 10L * 1024 * 1024 * 1024 // 10 GB
        storageRepository = StorageRepository(context)
    }

    @Test
    fun `getAvailableStorage returns formatted string`() = runBlocking {
        val result = storageRepository.getAvailableStorage()
        assertThat(result).contains("GB")
        assertThat(result).isNotEmpty()
    }
}