package com.heroassistant.ui.bloatware

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroassistant.repository.BloatwareRepository
import com.heroassistant.service.DeviceActor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BloatwareUiState(
    val bloatwareList: List<String> = emptyList()
)

@HiltViewModel
class BloatwareViewModel @Inject constructor(
    private val bloatwareRepository: BloatwareRepository,
    private val deviceActor: DeviceActor
) : ViewModel() {

    private val _uiState = MutableStateFlow(BloatwareUiState())
    val uiState: StateFlow<BloatwareUiState> = _uiState.asStateFlow()

    init {
        loadBloatware()
    }

    private fun loadBloatware() {
        viewModelScope.launch {
            val list = bloatwareRepository.detectBloatware()
            _uiState.value = BloatwareUiState(bloatwareList = list)
        }
    }

    fun remove(packageName: String) {
        viewModelScope.launch {
            deviceActor.uninstallApp(packageName)
            loadBloatware()
        }
    }
}