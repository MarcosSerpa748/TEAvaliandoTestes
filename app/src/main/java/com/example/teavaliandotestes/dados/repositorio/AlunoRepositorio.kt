package com.example.teavaliandotestes.dados.repositorio

import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AlunoRepositorio{

    suspend fun inserirAluno(nome:String,dataNascimento: LocalDate,nomeProfessora:String,turma:String)
    suspend fun deletarAluno(aluno: AlunoEntity)
    suspend fun todosAlunos(): Flow<List<AlunoEntity>>
}