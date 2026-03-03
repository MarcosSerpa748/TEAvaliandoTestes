package com.example.teavaliandotestes.presentation.states

import java.time.LocalDate

data class TelaLoginUIState(
    val nomeAluno:String = "",
    val dataNascimento: LocalDate? = null,
    val nomeProfessora:String = "",
    val turma:String = "",
    val abrirTecladoData:Boolean = false
)