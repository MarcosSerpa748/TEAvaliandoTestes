package com.example.teavaliandotestes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.teavaliandotestes.presentation.ui.screens.SegundaTela
import com.example.teavaliandotestes.presentation.ui.screens.TelaLogin
import com.example.teavaliandotestes.presentation.ui.screens.TelaTeste



@Composable
fun Navegador(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TelaLogin){
        composable<TelaLogin> {
            TelaLogin(validarNavegacao = {idAluno ->
                navController.navigate(SegundaTela(idAluno))
            })
        }
        composable<SegundaTela> {
            val valoresPassados = it.toRoute<SegundaTela>()
            TelaTeste(valoresPassados.idAluno, validarNavegacao = {navController.navigate(TelaLogin)})
        }
    }
}