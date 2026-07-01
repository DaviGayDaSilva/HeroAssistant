package com.heroassistant.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroassistant.repository.AppsRepository
import com.heroassistant.repository.BatteryRepository
import com.heroassistant.repository.DataUsageRepository
import com.heroassistant.repository.StorageRepository
import com.heroassistant.util.ShizukuChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val shizukuActive: Boolean = false,
    val batteryLevel: Int = 0,
    val storageAvailable: String = "0 GB",
    val dataUsage: String = "0 MB",
    val scanning: Boolean = false
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val batteryRepository: BatteryRepository,
    private val storageRepository: StorageRepository,
    private val dataUsageRepository: DataUsageRepository,
    private val appsRepository: AppsRepository,
    private val shizukuChecker: ShizukuChecker
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                shizukuActive = shizukuChecker.isShizukuAvailable(),
                batteryLevel = batteryRepository.getBatteryLevel(),
                storageAvailable = storageRepository.getAvailableStorage(),
                dataUsage = dataUsageRepository.getTodayDataUsage()
            )
        }
    }

    fun performQuickScan() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(scanning = true)
            // Aqui chamaria o scanner de verdade
            kotlinx.coroutines.delay(2000)
            _uiState.value = _uiState.value.copy(scanning = false)
        }
    }
}