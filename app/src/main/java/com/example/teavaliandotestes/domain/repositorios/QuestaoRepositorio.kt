package com.example.teavaliandotestes.domain.repositorios

import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao

interface QuestaoRepositorio{

    suspend fun pegarQuestaoPorCategoria(valor: CategoriaQuestao):List<QuestaoEntity>
}