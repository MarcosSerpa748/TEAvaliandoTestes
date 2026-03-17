package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.teavaliandotestes.presentation.viewmodels.TelaProvaViewModel

@Composable
fun TelaProva(
    navController: NavController,
    viewModel: TelaProvaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uistate.collectAsStateWithLifecycle()
    val contexto = LocalContext.current

    LaunchedEffect(uiState.provaFinalizada) {
        if (uiState.provaFinalizada) {
            println("A prova acabou! Navegar para relatório.")
        }
    }

    if (uiState.carregando) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (uiState.questaoAtual == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Nenhuma questão encontrada. Verifique o banco de dados.")
        }
        return
    }

    val questao = uiState.questaoAtual!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Fundo cinza bem clarinho
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Cabeçalho (Progresso)
        Text(
            text = "Questão ${uiState.indicieQuestaoAtual + 1} de ${uiState.questoes.size}",
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 4. Pergunta Principal + Botão de Áudio
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = questao.enunciado,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            BotaoOuvirEnunciado(textoParaFalar = questao.enunciado)

        }
        Spacer(modifier = Modifier.height(16.dp))
        // 2. Imagem de Apoio (Opcional)
        if (questao.imagemApoio != null) {
            val idImagemApoio = remember(questao.imagemApoio) {
                contexto.resources.getIdentifier(questao.imagemApoio, "drawable", contexto.packageName)
            }
            if (idImagemApoio != 0) {
                Image(
                    painter = painterResource(id = idImagemApoio),
                    contentDescription = "Imagem de apoio",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // 3. Texto de Apoio (Opcional)
        if (questao.textApoio != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = questao.textApoio,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // 5. As 4 Opções de Resposta (Usando Grid 2x2 para ficar bonito)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(questao.itens) { indice, opcao ->
                CartaoOpcao(
                    opcao = opcao,
                    selecionada = uiState.itemSelecionado == indice,
                    onClick = { viewModel.selecionarOpcao(indice) }
                )
            }
        }

        Button(
            onClick = { viewModel.confirmarResposta() },
            enabled = uiState.itemSelecionado != null, // Só libera se o aluno escolher algo
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = if (uiState.indicieQuestaoAtual == uiState.questoes.size - 1) "Finalizar Prova" else "Confirmar e Avançar",
                fontSize = 18.sp
            )
        }
    }
}