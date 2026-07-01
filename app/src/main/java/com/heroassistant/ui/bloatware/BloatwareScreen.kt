package com.heroassistant.ui.bloatware

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BloatwareScreen(viewModel: BloatwareViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Removedor de Bloatware", style = MaterialTheme.typography.headlineSmall)
        if (state.bloatwareList.isEmpty()) {
            Text("Nenhum bloatware detectado.")
        } else {
            state.bloatwareList.forEach { pkg ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(pkg)
                    Button(onClick = { viewModel.remove(pkg) }) {
                        Text("Remover")
                    }
                }
            }
        }
    }
}