package com.heroassistant.ui.cleaner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CleanerScreen(viewModel: CleanerViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Limpeza Rápida", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Cache total estimado: ${state.estimatedCache}")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.cleanAllCache() },
            enabled = !state.cleaning,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.cleaning) "Limpando..." else "Limpar Cache de Todos os Apps")
        }
        if (state.message.isNotEmpty()) {
            Text(state.message, color = MaterialTheme.colorScheme.primary)
        }
    }
}