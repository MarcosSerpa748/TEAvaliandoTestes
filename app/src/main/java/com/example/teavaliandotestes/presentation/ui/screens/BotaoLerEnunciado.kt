package com.example.teavaliandotestes.presentation.ui.screens


import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.teavaliandotestes.ui.theme.PastelBlue
import java.util.Locale

@Composable
fun BotaoOuvirEnunciado(textoParaFalar: String) {
    val contexto = LocalContext.current
    var tts: TextToSpeech? by remember { mutableStateOf(null) }

    DisposableEffect(contexto) {
        tts = TextToSpeech(contexto) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("pt", "BR")
            }
        }
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    IconButton(
        onClick = { tts?.speak(textoParaFalar, TextToSpeech.QUEUE_FLUSH, null, null) },
        modifier = Modifier.size(48.dp).background(color = PastelBlue, shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Ouvir enunciado",
            tint = Color.Black,
            modifier = Modifier.size(36.dp)
        )
    }
}