package com.example.teavaliandotestes.usecases

import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import com.example.teavaliandotestes.dados.repositorio.AlunoRepositorio
import javax.inject.Inject


class DeletarAlunoUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    suspend operator fun invoke(aluno: AlunoEntity){
        alunoRepositorio.deletarAluno(aluno)
    }
}