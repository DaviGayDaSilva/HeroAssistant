package com.heroassistant.repository

import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BatteryRepositoryTest {

    private lateinit var context: Context
    private lateinit var batteryRepository: BatteryRepository

    @Before
    fun setUp() {
        context = mockk(relaxed = true)
        val intent = mockk<Intent>(relaxed = true)
        every { intent.getIntExtra(BatteryManager.EXTRA_LEVEL, any()) } returns 75
        every { intent.getIntExtra(BatteryManager.EXTRA_SCALE, any()) } returns 100
        every { context.registerReceiver(any(), any()) } returns intent
        batteryRepository = BatteryRepository(context)
    }

    @Test
    fun `getBatteryLevel returns percentage`() = runBlocking {
        val level = batteryRepository.getBatteryLevel()
        assertThat(level).isEqualTo(75)
    }
}