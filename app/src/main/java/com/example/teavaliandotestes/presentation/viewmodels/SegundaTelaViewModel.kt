package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SegundaTelaViewModel@Inject constructor(): ViewModel(){

    private val _validarNavegacao = Channel<Unit>()
    val validarNavegacao = _validarNavegacao.receiveAsFlow()


    fun navegarParaOutraTela(){
        viewModelScope.launch {
            _validarNavegacao.send(Unit)
        }
    }
}