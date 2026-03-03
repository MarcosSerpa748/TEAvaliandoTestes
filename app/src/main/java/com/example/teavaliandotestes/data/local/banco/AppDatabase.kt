package com.example.teavaliandotestes.data.local.banco

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teavaliandotestes.data.conversor.Conversor
import com.example.teavaliandotestes.data.local.daos.AlunoDAO
import com.example.teavaliandotestes.data.local.entidades.AlunoEntity

@Database(entities = [AlunoEntity::class], version = 1)
@TypeConverters(Conversor::class)
abstract class AppDatabase(): RoomDatabase(){

    abstract fun gerarDaoAluno(): AlunoDAO
}