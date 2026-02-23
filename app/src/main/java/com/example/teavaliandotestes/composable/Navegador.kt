package com.example.teavaliandotestes.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


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