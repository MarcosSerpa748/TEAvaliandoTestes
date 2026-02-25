package com.example.teavaliandotestes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teavaliandotestes.dados.entidade.AlunoEntity
import com.example.teavaliandotestes.dados.repositorio.AlunoRepositorio
import com.example.teavaliandotestes.usecases.InserirAlunoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class TelaLoginUIState(
    val nomeAluno:String = "",
    val dataNascimento: LocalDate? = null,
    val nomeProfessora:String = "",
    val turma:String = ""
)

@HiltViewModel
class TelaLoginViewModel@Inject constructor(private val inserirAlunoUseCase: InserirAlunoUseCase): ViewModel(){

    private val _uiState = MutableStateFlow(TelaLoginUIState())
    val uiState = _uiState.asStateFlow()

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

    fun salvarAluno() {
        val dataVerificada = _uiState.value.dataNascimento ?: return

        viewModelScope.launch {
            inserirAlunoUseCase (_uiState.value.nomeAluno, dataVerificada,_uiState.value.nomeProfessora,_uiState.value.turma)
            alterarNome("")
            alterarData(null)
            alterarNomeProfessora("")
            alterarNomeProfessora("")
        }
    }

}