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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.teavaliandotestes.presentation.viewmodels.SegundaTelaViewModel
import com.example.teavaliandotestes.ui.theme.PastelBlue
import kotlinx.serialization.Serializable

@Serializable
data class SegundaTela(val idAluno:Int)

@Composable
fun TelaTeste(idAluno:Int, viewModel: SegundaTelaViewModel = hiltViewModel(), validarNavegacao:(Unit) ->Unit){

    LaunchedEffect(Unit) {
        viewModel.validarNavegacao.collect { permissao ->
            validarNavegacao(permissao)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = PastelBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text = "Aluno cadastrado com sucesso!\nSeu ID:$idAluno")
        Button(onClick = {
            viewModel.navegarParaOutraTela()
        }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            Text(text = "Voltar")
        }
    }
}