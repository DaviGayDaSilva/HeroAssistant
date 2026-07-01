package com.heroassistant.ui.schedule

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ScheduleScreen(viewModel: ScheduleViewModel = hiltViewModel()) {
    var scheduleEnabled by remember { mutableStateOf(false) }
    var frequency by remember { mutableStateOf("diária") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Limpeza Automática", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Switch(checked = scheduleEnabled, onCheckedChange = {
                scheduleEnabled = it
                viewModel.setSchedule(it, frequency)
            })
            Text("Ativar limpeza automática")
        }
        if (scheduleEnabled) {
            OutlinedTextField(
                value = frequency,
                onValueChange = { frequency = it },
                label = { Text("Frequência") }
            )
        }
    }
}