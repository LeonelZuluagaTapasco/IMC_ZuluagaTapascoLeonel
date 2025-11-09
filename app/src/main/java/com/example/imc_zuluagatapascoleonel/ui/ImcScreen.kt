package com.example.imc_zuluagatapascoleonel.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.com.example.imc_zuluagatapascoleonel.viewmodel.ImcViewModel
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImcScreen(viewModel: ImcViewModel) {
    val peso = viewModel.peso
    val altura = viewModel.altura
    val errorPeso = viewModel.errorPeso
    val errorAltura = viewModel.errorAltura
    val resultado = viewModel.resultado

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Calculadora IMC", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = peso,
                onValueChange = { viewModel.onPesoChange(it) },
                label = { Text("Peso (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorPeso != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errorPeso != null) {
                Text(text = errorPeso, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            OutlinedTextField(
                value = altura,
                onValueChange = { viewModel.onAlturaChange(it) },
                label = { Text("Altura (cm)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorAltura != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errorAltura != null) {
                Text(text = errorAltura, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { viewModel.calcularImc() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Calcular")
                }
                OutlinedButton(
                    onClick = { viewModel.limpiarCampos() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpiar")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            resultado?.let { res ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(res.color), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "IMC: ${"%.2f".format(res.imc)}", style = MaterialTheme.typography.titleMedium)
                    Text(text = res.classification, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}