package com.example.teavaliandotestes.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teavaliandotestes.data.local.entidades.ResultadoProvaEntity

@Dao
interface ResultadoProvaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirResultadoProva(resultadoProva: ResultadoProvaEntity)

    @Query("SELECT * FROM resultado_prova WHERE idPai = :idPai ORDER BY dataProva DESC")
    suspend fun buscarResultados(idPai:Int):List<ResultadoProvaEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM alunos WHERE id = :id)")
    suspend fun verificarExistenciaID(id:Int): Boolean
}