package com.example.teavaliandotestes.dados.repositorio

import com.example.teavaliandotestes.dados.dao.AlunoDAO
import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import java.time.LocalDate

class AlunoRepository(private val alunoDAO: AlunoDAO){

    val dados = alunoDAO.todosAlunos()

    suspend fun adicionarAluno(nome: String, dataNascimento: LocalDate, nomeProfessora:String, turma:String){
        val aluno = AlunoEntity(
            nome = nome,
            nomeProfessora = nomeProfessora,
            dataNascimento = dataNascimento,
            turma = turma
        )

        alunoDAO.adicionarAluno(aluno)
    }

    suspend fun deletarAluno(aluno: AlunoEntity){
        alunoDAO.deletarAluno(aluno)
    }
}