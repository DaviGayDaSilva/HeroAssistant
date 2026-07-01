package com.heroassistant.ui.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroassistant.repository.AppsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScannerUiState(
    val loading: Boolean = false,
    val heavyApps: List<String> = emptyList()
)

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val appsRepository: AppsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScannerUiState())
    val uiState: StateFlow<ScannerUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val apps = appsRepository.getHeavyApps()
            _uiState.value = _uiState.value.copy(loading = false, heavyApps = apps)
        }
    }
}