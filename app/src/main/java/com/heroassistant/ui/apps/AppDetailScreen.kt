package com.heroassistant.ui.apps

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppDetailScreen(packageName: String, viewModel: AppListViewModel = hiltViewModel()) {
    // Placeholder para detalhes do app
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Detalhes: $packageName")
    }
}