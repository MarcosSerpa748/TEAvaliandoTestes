package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teavaliandotestes.domain.usecases.InserirAlunoUseCase
import com.example.teavaliandotestes.presentation.uistates.TelaLoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject



@HiltViewModel
class TelaLoginViewModel@Inject constructor(private val inserirAlunoUseCase: InserirAlunoUseCase): ViewModel(){

    private val _uiState = MutableStateFlow(TelaLoginUIState())
    val uiState = _uiState.asStateFlow()

    private val _mensagemError = Channel<String>()
    val mensagemError = _mensagemError.receiveAsFlow()

    private val _validarNavegacao = Channel<Int>()
    val validarNavegacao = _validarNavegacao.receiveAsFlow()

    fun alterarNome(valor:String){
        _uiState.update { valorAtual ->
            valorAtual.copy(nomeAluno = valor)
        }
    }

    fun alterarData(valor: LocalDate?){
        _uiState.update { valorAtual ->
            valorAtual.copy(dataNascimento = valor)
        }
    }

    fun alterarNomeProfessora(valor:String){
        _uiState.update { valorAtual ->
            valorAtual.copy(nomeProfessora = valor)
        }
    }
    fun alterarTurma(valor:String){
        _uiState.update { valorAtual ->
            valorAtual.copy(turma = valor)
        }
    }

    fun alterarEstadoTecladoData(valor:Boolean){
        _uiState.update { valorAtual ->
            valorAtual.copy(abrirTecladoData = valor)
        }
    }

    fun salvarAluno() {

        viewModelScope.launch {
            val dataVerificada = _uiState.value.dataNascimento

            if (dataVerificada == null) {
                _mensagemError.send("O campo da data está vazio!")
                return@launch
            }

            try {
                 val idAluno = inserirAlunoUseCase (_uiState.value.nomeAluno, dataVerificada,_uiState.value.nomeProfessora,_uiState.value.turma)
                alterarNome("")
                alterarData(null)
                alterarNomeProfessora("")
                alterarTurma("")
                _validarNavegacao.send(idAluno.toInt())
            }catch (e:Exception){
                _mensagemError.send(e.message ?:"Erro desconhecido!")
            }
        }
    }
}
