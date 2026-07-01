package com.heroassistant.ui.cleaner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroassistant.service.DeviceActor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CleanerUiState(
    val estimatedCache: String = "0 MB",
    val cleaning: Boolean = false,
    val message: String = ""
)

@HiltViewModel
class CleanerViewModel @Inject constructor(
    private val deviceActor: DeviceActor
) : ViewModel() {

    private val _uiState = MutableStateFlow(CleanerUiState())
    val uiState: StateFlow<CleanerUiState> = _uiState.asStateFlow()

    fun cleanAllCache() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(cleaning = true, message = "")
            val success = deviceActor.trimAllCaches()
            _uiState.value = _uiState.value.copy(
                cleaning = false,
                message = if (success) "Cache limpo com sucesso!" else "Falha ao limpar cache."
            )
        }
    }
}