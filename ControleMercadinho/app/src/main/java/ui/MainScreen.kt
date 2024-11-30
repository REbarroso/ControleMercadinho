package com.example.controlemercadinho.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(onNavigateToStock: () -> Unit, onNavigateToSales: () -> Unit, onNavigateToReports: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onNavigateToStock,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gerenciar Estoque")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToSales,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gerenciar Vendas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToReports,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Relatórios")
        }
    }
}
