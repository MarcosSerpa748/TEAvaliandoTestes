package com.example.teavaliandotestes.dados.entidade

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "alunos")
data class AlunoEntity(
    @PrimaryKey(autoGenerate = true)val id:Int = 0,
    val nome:String,
    val nomeProfessora:String,
    val dataNascimento: LocalDate,
    val turma:String
)
