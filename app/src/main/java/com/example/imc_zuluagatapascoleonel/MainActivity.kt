package com.example.imc_zuluagatapascoleonel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imc_zuluagatapascoleonel.ui.theme.IMC_ZuluagaTapascoLeonelTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.imc_zuluagatapascoleonel.ImcScreen
import com.example.imc_zuluagatapascoleonel.ImcViewModel


class MainActivity : ComponentActivity() {
    private val vm: ImcViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCTheme {
                ImcScreen(viewModel = vm)
            }
        }
    }
}