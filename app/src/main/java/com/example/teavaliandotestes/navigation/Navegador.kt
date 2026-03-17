package com.example.teavaliandotestes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.teavaliandotestes.navigation.rotas.TelaCadastroRoute
import com.example.teavaliandotestes.navigation.rotas.TelaProvaRoute
import com.example.teavaliandotestes.presentation.ui.screens.TelaCadastro
import com.example.teavaliandotestes.presentation.ui.screens.TelaProva



@Composable
fun Navegador(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TelaCadastroRoute){
        composable<TelaCadastroRoute> {
            TelaCadastro(validarNavegacao = { idAluno ->
                navController.navigate(TelaProvaRoute(idAluno))
            })
        }
        composable<TelaProvaRoute> {
            val valoresPassados = it.toRoute<TelaProvaRoute>()
            TelaProva(navController)
        }
    }
}