package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.teavaliandotestes.ui.theme.PastelBlue
import kotlinx.serialization.Serializable

@Serializable
object TelaTeste

@Composable
fun TelaTeste(navController: NavController){
    Column(
        Modifier
            .fillMaxSize()
            .background(color = PastelBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text = "Aluno castrado!")
        Button(onClick = {
            navController.navigate(TelaLogin)
        }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            Text(text = "Voltar")
        }
    }
}