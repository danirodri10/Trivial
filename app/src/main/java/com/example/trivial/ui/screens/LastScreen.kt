package com.example.trivial.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trivial.ui.state.VM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastScreen(
    navigateToFirstScreen: () -> Unit,
    viewModel: VM,
) {
    val state by viewModel.state.collectAsState()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Pantalla Final",
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomText("Ha finalizado el juego. Para volver a jugar, vuelve a la pantalla de inicio")
            CustomText("Puntuación: ${state.points}")
            CustomText("Preguntas acertadas: ${state.correctAnswers}")
            CustomText("Preguntas falladas: ${state.wrongAnswers}")

            Button(
                modifier = Modifier.padding(40.dp),
                onClick = {
                    viewModel.updateRecord()
                    navigateToFirstScreen()
                    viewModel.resetGame()
                    viewModel.resetEnableds()
                }
            ) {
                Text(
                    text = "Volver al inicio",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LastScreenPreview() {
    LastScreen({}, viewModel = VM())
}