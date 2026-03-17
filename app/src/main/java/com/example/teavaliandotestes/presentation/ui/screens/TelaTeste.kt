package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.teavaliandotestes.presentation.viewmodels.TelaProvaViewModel
import com.example.teavaliandotestes.ui.theme.PastelBlue


@Composable
fun TelaTeste(idAluno:Int, viewModel: TelaProvaViewModel = hiltViewModel(), validarNavegacao:(Unit) ->Unit){


    LaunchedEffect(Unit){
        viewModel.permitirNavegacao.collect {
            validarNavegacao(it)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = PastelBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text(text = "ID do aluno:$idAluno")
        Button(onClick = {
            viewModel.lancarNavegacao()
        }){
            Text("Voltar para tela cadastro")
        }
    }
}