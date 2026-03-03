package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.domain.repositorios.AlunoRepositorio
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

class InserirAlunoUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    suspend operator fun invoke(nomeAluno:String, dataNascimento: LocalDate, nomeProfessora:String, turma: String){
        if (nomeAluno.isBlank()){
            throw Exception("Campo do nome está vazio")
        }
        if (nomeProfessora.isBlank()){
            throw Exception("O campo nome da professora está vazio!")
        }
        if (turma.isBlank()){
            throw Exception("O campo turma está vazio!")
        }

        alunoRepositorio.inserirAluno(nomeAluno,dataNascimento,nomeProfessora,turma)
    }
}