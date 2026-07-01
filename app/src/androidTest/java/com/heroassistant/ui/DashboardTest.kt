package com.heroassistant.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heroassistant.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun dashboard_displaysBatteryInfo() {
        composeTestRule.onNodeWithTag("battery_card").assertIsDisplayed()
    }

    @Test
    fun dashboard_displaysStorageInfo() {
        composeTestRule.onNodeWithTag("storage_card").assertIsDisplayed()
    }

    @Test
    fun dashboard_displaysDataUsageInfo() {
        composeTestRule.onNodeWithTag("data_usage_card").assertIsDisplayed()
    }

    @Test
    fun dashboard_showsShizukuStatus() {
        composeTestRule
            .onNodeWithTag("shizuku_status_indicator")
            .assertIsDisplayed()
    }

    @Test
    fun dashboard_showsScanButton() {
        composeTestRule.onNodeWithTag("btn_quick_scan").assertIsDisplayed()
    }
}