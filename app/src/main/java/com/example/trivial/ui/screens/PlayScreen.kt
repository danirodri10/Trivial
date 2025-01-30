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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivial.model.Question
import com.example.trivial.ui.state.VM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayScreen(
    navigateToLastScreen: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: VM = viewModel(factory = VM.Factory),
) {
    val state by viewModel.state.collectAsState()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    //le ponemos +1 para que no parta de 0 (la lista parte del índice 0, pero no queremos una pregunta 0)
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
            ContentPlayScreen(
                points = state.points,
                question = viewModel.getCurrentQuestion(),
                state.resultText,
                enabledNavigate = state.check,
                enabledOptions = state.checkOptions,
                onOptionClicked = { selectedOption -> viewModel.onOptionClicked(selectedOption) },
                nextQuestion = { viewModel.nextQuestion() },
                resetEnableds = { viewModel.resetEnableds() },
                questionsQuantity = state.questionsQuantity,
                currentQuestion = state.currentQuestionIndex,
                navigateToLastScreen = navigateToLastScreen,
            )
        }
    }
}

@Composable
fun ContentPlayScreen(
    points: Int,
    question: Question,
    finalText: String,
    enabledNavigate: Boolean,
    enabledOptions: Boolean,
    onOptionClicked: (String) -> Unit,
    nextQuestion: () -> Unit,
    resetEnableds: () -> Unit,
    questionsQuantity: Int,
    currentQuestion: Int,
    navigateToLastScreen: () -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar la puntuación
        Text(
            text = "Puntuación: $points", // Asegúrate de que `score` está disponible en `Question`
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Tarjeta con la pregunta y opciones
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Pregunta
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomText("Pregunta: ${question.question}")
                }

                // Opciones de respuesta
                question.options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                onOptionClicked(option)
                            },
                            enabled = enabledOptions,
                        ) {
                            Text(
                                text = option
                            )
                        }
                    }
                }

                // Texto de resultado
                CustomText(text = "Clicka una de las opciones")
                if (!enabledOptions) {
                    CustomText(finalText)
                }
            }
        }

        // Botón de siguiente pregunta
        Button(
            onClick = {
                //le restamos -1 al total de preguntas, ya que al partir de 0 en la lista hay que restarle 1 al total
                if (currentQuestion < questionsQuantity - 1) {
                    nextQuestion()
                    resetEnableds()
                } else {
                    navigateToLastScreen()
                }
            },
            enabled = enabledNavigate
        ) {
            Text("Siguiente Pregunta")
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Siguiente pregunta"
            )
        }
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

/*@Preview(showBackground = true)
@Composable
fun PlayScreenPreview() {
    PlayScreen({}, {}, viewModel = VM())
}*/