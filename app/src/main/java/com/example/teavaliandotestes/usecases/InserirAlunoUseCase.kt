package com.example.teavaliandotestes.usecases

import com.example.teavaliandotestes.dados.repositorio.AlunoRepositorio
import java.time.LocalDate
import javax.inject.Inject

class InserirAlunoUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    suspend operator fun invoke(nomeAluno:String,dataNascimento: LocalDate,nomeProfessora:String,turma: String){
        if (nomeAluno.isBlank()) return
        if (nomeProfessora.isEmpty()) return
        if (turma.isBlank()) return

        alunoRepositorio.inserirAluno(nomeAluno,dataNascimento,nomeProfessora,turma)
    }
}