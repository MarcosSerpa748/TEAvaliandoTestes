package com.example.teavaliandotestes.usecases

import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import com.example.teavaliandotestes.dados.repositorio.AlunoRepositorio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ExibirAlunosUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    operator fun invoke(): Flow<List<AlunoEntity>>{
        return alunoRepositorio.todosAlunos()
    }
}