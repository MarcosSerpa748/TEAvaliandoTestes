package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import com.example.teavaliandotestes.domain.usecases.GerarProvaUseCase
import com.example.teavaliandotestes.presentation.uistates.TelaProvaUIState
import com.example.teavaliandotestes.presentation.ui.screens.SegundaTela
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelaProvaViewModel@Inject constructor(
    private val gerarProvaUseCase: GerarProvaUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _uiState = MutableStateFlow(TelaProvaUIState())
    val uistate = _uiState.asStateFlow()

    private val valoresPassados = savedStateHandle.toRoute<SegundaTela>()
    val idAluno = valoresPassados.idAluno


    private val pontuacoes = mutableMapOf<CategoriaQuestao,Int>().withDefault { 0 }

    init {
        gerarProva()
    }
    fun gerarProva(){
        viewModelScope.launch {
            try {
                val provas = gerarProvaUseCase()

                _uiState.update { valorAtual ->
                    valorAtual.copy(carregando = false, questoes = provas)
                }
            }catch (e: Exception){
                _uiState.update { valorAtual ->
                    valorAtual.copy(mensagemError = e.message)
                }
            }

        }
    }
    fun selecionarOpcao(valor:Int){
        _uiState.update { valorAtual ->
            valorAtual.copy(itemSelecionado = valor)
        }
    }

    fun confirmarResposta(){
        val estadoAtual = _uiState.value
        val questao = estadoAtual.questaoAtual ?: return
        val itemSelecionado = estadoAtual.itemSelecionado ?: return

        if (itemSelecionado == questao.indicieItenCorreto){
            val pontosAtuais = pontuacoes.getValue(questao.categoria)
            pontuacoes[questao.categoria] = pontosAtuais + 1
        }
        if (estadoAtual.indicieQuestaoAtual < estadoAtual.questoes.size){
            _uiState.update { valorAtual ->
                valorAtual.copy(indicieQuestaoAtual = valorAtual.indicieQuestaoAtual + 1, itemSelecionado = null)
            }
        }else{
            finalizarProva()
        }
    }

    private fun finalizarProva() {
        _uiState.update { valorAtual ->
            valorAtual.copy(provaFinalizada = true)
        }
    }
}