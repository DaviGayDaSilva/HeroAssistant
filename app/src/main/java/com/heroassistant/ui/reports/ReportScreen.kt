package com.heroassistant.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReportScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Relatórios de Desempenho", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Text("Bateria: uso médio diário", modifier = Modifier.padding(16.dp))
        }
    }
}