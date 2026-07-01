package com.heroassistant.repository

import android.content.Context
import android.content.pm.PackageManager
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AppsRepositoryTest {

    private lateinit var context: Context
    private lateinit var packageManager: PackageManager
    private lateinit var appsRepository: AppsRepository

    @Before
    fun setUp() {
        context = mockk(relaxed = true)
        packageManager = mockk(relaxed = true)
        every { context.packageManager } returns packageManager
        every { packageManager.getInstalledApplications(any()) } returns emptyList()
        appsRepository = AppsRepository(context)
    }

    @Test
    fun `getHeavyApps returns list`() = runBlocking {
        val result = appsRepository.getHeavyApps()
        assertThat(result).isNotNull()
        assertThat(result).isEmpty()
    }
}