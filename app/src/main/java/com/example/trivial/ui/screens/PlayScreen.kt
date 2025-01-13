package com.example.trivial.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.trivial.model.Question
import com.example.trivial.ui.state.VM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayScreen(
    navigateToSecondGiftScreen: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: VM,
) {
    val state by viewModel.state.collectAsState()

    /*
    * ME HE QUEDADO EN IMPLEMENTAR LOS MÉTODOS PARA CAMBIAR DE PREGUNTA DEL VIEWMODEL
    * CHATGPT: PREGUNTAS PARA TRIVIAL
    * */

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Pregunta: ${state.currentQuestionIndex + 1}/${state.questionsQuantity}")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver Atrás",
                        )
                    }
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
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstGiftContentScreen(
                question = viewModel.getCurrentQuestion(),
                state.finalText,
                enabledNavigate = state.check,
                enabledOptions = state.checkOptions,
                navigateToSecondGiftScreen = navigateToSecondGiftScreen
            )
        }
    }
}

@Composable
fun FirstGiftContentScreen(
    question: Question,
    finalText: String,
    enabledNavigate: Boolean,
    enabledOptions: Boolean,
    //updateFinalText: () -> Unit,
    navigateToSecondGiftScreen: () -> Unit,
) {
    Card {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomText("Pregunta: ${question.text}")
        }
        question.options.forEach { option ->
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {/**/ },
                    enabled = enabledOptions
                ) {
                    Text(
                        text = option
                    )
                }
            }
        }
        CustomText(text = "Clicka una de las opciones")
    }
    CustomText(finalText)
    Button(
        onClick = { navigateToSecondGiftScreen() },
        enabled = enabledNavigate
    ) {
        Text("Siguiente Pregunta")
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "siguiente regalo"
        )
    }
}

@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(20.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview(showBackground = true)
@Composable
fun FirstGiftScreenPreview() {
    PlayScreen({}, {}, viewModel = VM())
}