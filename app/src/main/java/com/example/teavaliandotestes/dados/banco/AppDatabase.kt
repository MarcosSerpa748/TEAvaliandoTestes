package com.example.teavaliandotestes.dados.banco

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teavaliandotestes.dados.conversor.Conversor
import com.example.teavaliandotestes.dados.dao.AlunoDAO
import com.example.teavaliandotestes.dados.entidade.AlunoEntity

@Database(entities = [AlunoEntity::class], version = 1)
@TypeConverters(Conversor::class)
abstract class AppDatabase(): RoomDatabase(){

    abstract fun gerarDaoAluno(): AlunoDAO
}