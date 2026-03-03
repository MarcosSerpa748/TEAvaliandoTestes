package com.example.teavaliandotestes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teavaliandotestes.presentation.ui.screens.TelaLogin
import com.example.teavaliandotestes.presentation.ui.screens.TelaTeste


@Composable
fun Navegador(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TelaLogin){
        composable<TelaLogin> {
            TelaLogin(navController = navController)
        }
        composable<TelaTeste> {
            TelaTeste(navController = navController)
        }
    }
}