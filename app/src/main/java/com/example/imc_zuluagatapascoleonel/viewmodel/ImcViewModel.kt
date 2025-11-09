package com.example.imc_zuluagatapascoleonel.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.imc_zuluagatapascoleonel.model.ImcResult
import kotlin.math.pow
import kotlin.math.round

class ImcViewModel : ViewModel() {

    var peso by mutableStateOf("")
        private set

    var altura by mutableStateOf("")
        private set

    var errorPeso by mutableStateOf<String?>(null)
        private set

    var errorAltura by mutableStateOf<String?>(null)
        private set

    var resultado by mutableStateOf<ImcResult?>(null)
        private set

    fun onPesoChange(value: String) {
        peso = value
        if (value.isNotBlank()) errorPeso = null
    }

    fun onAlturaChange(value: String) {
        altura = value
        if (value.isNotBlank()) errorAltura = null
    }

    fun calcularImc() {
        // Aceptar coma como separador decimal
        val pesoVal = peso.replace(",", ".").toDoubleOrNull()
        val alturaCm = altura.replace(",", ".").toDoubleOrNull()

        errorPeso = if (pesoVal == null || pesoVal <= 0.0) "Ingrese un peso válido (> 0)" else null
        errorAltura = if (alturaCm == null || alturaCm <= 0.0) "Ingrese una altura válida (> 0)" else null

        if (errorPeso != null || errorAltura != null) {
            resultado = null
            return
        }

        val alturaM = alturaCm!! / 100.0
        val imcRaw = pesoVal!! / alturaM.pow(2.0)
        // Redondear a 2 decimales
        val imcRounded = (round(imcRaw * 100.0)) / 100.0

        val (clasificacion, color) = when {
            imcRounded < 18.5 -> Pair("Bajo peso", 0xFFBBDEFB) // Azul claro
            imcRounded < 25.0 -> Pair("Peso normal", 0xFFC8E6C9) // Verde claro
            imcRounded < 30.0 -> Pair("Sobrepeso", 0xFFFFF9C4) // Amarillo claro
            else -> Pair("Obesidad", 0xFFFFCDD2) // Rojo claro
        }

        resultado = ImcResult(imc = imcRounded, classification = clasificacion, color = color)
    }

    fun limpiarCampos() {
        peso = ""
        altura = ""
        errorPeso = null
        errorAltura = null
        resultado = null
    }
}