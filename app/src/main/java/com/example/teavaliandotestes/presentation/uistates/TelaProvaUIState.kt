package com.example.teavaliandotestes.presentation.uistates

import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity

data class TelaProvaUIState(
    val questoes:List<QuestaoEntity> = emptyList(),
    val indicieQuestaoAtual:Int = 0,
    val itemSelecionado:Int? = null,
    val carregando:Boolean = true,
    val mensagemError:String? = null,
    val provaFinalizada:Boolean = false
){
    val questaoAtual:QuestaoEntity?
        get() = if (questoes.isNotEmpty() && indicieQuestaoAtual < questoes.size){
            questoes[indicieQuestaoAtual]
        }else{
            null
        }
}
