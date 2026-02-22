package com.example.teavaliandotestes.dados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlunoDAO {

    @Insert
    suspend fun adicionarAluno(aluno: AlunoEntity)

    @Delete
    suspend fun deletarAluno(aluno: AlunoEntity)

    @Query("SELECT * FROM alunos")
    fun todosAlunos(): Flow<List<AlunoEntity>>
}