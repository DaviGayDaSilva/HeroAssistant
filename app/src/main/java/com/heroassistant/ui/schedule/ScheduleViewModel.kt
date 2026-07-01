package com.heroassistant.ui.schedule

import androidx.lifecycle.ViewModel
import com.heroassistant.worker.ScanScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scanScheduler: ScanScheduler
) : ViewModel() {

    fun setSchedule(enabled: Boolean, frequency: String) {
        if (enabled) {
            scanScheduler.schedulePeriodicClean(frequency)
        } else {
            scanScheduler.cancel()
        }
    }
}