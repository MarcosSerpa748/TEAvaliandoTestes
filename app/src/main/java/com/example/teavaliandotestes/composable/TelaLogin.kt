package com.example.teavaliandotestes.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.teavaliandotestes.R
import com.example.teavaliandotestes.ui.theme.ElectricBlue
import com.example.teavaliandotestes.ui.theme.PastelBlue
import com.example.teavaliandotestes.ui.theme.TEAvaliandoTestesTheme
import com.example.teavaliandotestes.viewmodel.TelaLoginViewModel
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Serializable
object TelaLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLogin(viewModel: TelaLoginViewModel = hiltViewModel(),navController: NavController) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val snackBarState = remember { SnackbarHostState() }
    var abrirTecladoDatas by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.mensagemError.collect {
            snackBarState.showSnackbar(message = it, duration = SnackbarDuration.Short)
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(snackBarState) }) { innerPadding ->

        Column(
            Modifier
                .fillMaxSize()
                .background(color = PastelBlue)
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "Título da tela de login",
                Modifier
                    .height(140.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(Modifier.size(150.dp))
            Image(
                painter = painterResource(R.drawable.teuzinho),
                contentDescription = "teuzinho",
                Modifier
                    .height(230.dp)
                    .width(400.dp)
            )
            OutlinedTextField(
                value = uiState.nomeAluno,
                onValueChange = { viewModel.alterarNome(it) },
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Face,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                ),
                placeholder = { Text(text = "Nome do aluno:") }
            )
            Spacer(Modifier.size(10.dp))
            OutlinedTextField(
                value = uiState.dataNascimento?.format(formatador) ?: "",
                onValueChange = {},
                readOnly = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                placeholder = { Text(text = "Nascimento do aluno:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Green,
                ),
                shape = RoundedCornerShape(25.dp),
                interactionSource = remember { MutableInteractionSource() }
                    .also { source ->
                        LaunchedEffect(source) {
                            source.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    abrirTecladoDatas = true
                                }
                            }
                        }
                    }
            )

            if (abrirTecladoDatas) {
                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = { abrirTecladoDatas = false },
                    confirmButton = {
                        TextButton(onClick = {
                            abrirTecladoDatas = false

                            datePickerState.selectedDateMillis?.let {
                                val dataConvertida =
                                    Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()

                                viewModel.alterarData(dataConvertida)
                            }
                        }) {
                            Text(text = "Confirmar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { abrirTecladoDatas = false }) {
                            Text(text = "Cancelar")
                        }
                    }) {
                    DatePicker(datePickerState)
                }
            }
            Spacer(Modifier.size(10.dp))
            OutlinedTextField(
                value = uiState.nomeProfessora,
                onValueChange = { viewModel.alterarNomeProfessora(it) },
                placeholder = { Text(text = "Nome da professora:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = ElectricBlue,
                    unfocusedBorderColor = ElectricBlue,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                shape = RoundedCornerShape(25.dp)
            )
            Spacer(Modifier.size(10.dp))
            OutlinedTextField(
                value = uiState.turma,
                onValueChange = { viewModel.alterarTurma(it) },
                placeholder = { Text(text = "Nome da turma:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Yellow,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                shape = RoundedCornerShape(25.dp)
            )
            Button(
                onClick = {
                    viewModel.salvarAluno()
                }){
                Text("Cadastrar aluno")
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}
@Preview
@Composable
private fun TelaLoginPreviw(){
    TEAvaliandoTestesTheme(){
        TelaLogin(navController = rememberNavController())
    }
}
