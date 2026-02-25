package com.example.teavaliandotestes.dados.repositorio

import com.example.teavaliandotestes.dados.dao.AlunoDAO
import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject


class AlunoRepositorioimpl@Inject constructor(private val dao: AlunoDAO): AlunoRepositorio{
    override suspend fun inserirAluno(nome: String, dataNascimento: LocalDate, nomeProfessora: String, turma: String) {

        val aluno = AlunoEntity(nome = nome,dataNascimento = dataNascimento, nomeProfessora = nomeProfessora,turma = turma)
        dao.adicionarAluno(aluno)
    }

    override suspend fun deletarAluno(aluno: AlunoEntity){
        dao.deletarAluno(aluno)
    }

    override fun todosAlunos(): Flow<List<AlunoEntity>> {
        return dao.todosAlunos()
    }

}