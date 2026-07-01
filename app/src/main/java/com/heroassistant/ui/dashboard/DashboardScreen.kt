package com.heroassistant.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Indicador de status do Shizuku
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("shizuku_status_indicator"),
            colors = CardDefaults.cardColors(
                containerColor = if (uiState.shizukuActive)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Text(
                text = if (uiState.shizukuActive) "Shizuku Ativo" else "Shizuku Inativo",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Métricas principais
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .testTag("battery_card")
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Bateria", style = MaterialTheme.typography.labelMedium)
                    Text("${uiState.batteryLevel}%", style = MaterialTheme.typography.headlineSmall)
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .testTag("storage_card")
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Armazenamento", style = MaterialTheme.typography.labelMedium)
                    Text(uiState.storageAvailable, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("data_usage_card")
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Dados Móveis Hoje", style = MaterialTheme.typography.labelMedium)
                Text(uiState.dataUsage, style = MaterialTheme.typography.headlineSmall)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.performQuickScan() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("btn_quick_scan"),
            enabled = !uiState.scanning
        ) {
            Text(if (uiState.scanning) "Escaneando..." else "Escanear Dispositivo")
        }
    }
}