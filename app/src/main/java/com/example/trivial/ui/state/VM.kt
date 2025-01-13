package com.example.trivial.ui.state

import androidx.lifecycle.ViewModel
import com.example.trivial.model.Question
import com.example.trivial.ui.data.QuestionData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VM : ViewModel() {
    private val _state =
        MutableStateFlow(State())
    val state: StateFlow<State> =
        _state.asStateFlow()

    // Incrementar la cantidad de preguntas
    fun incrementQuestions(maxQuestions: Int) {
        _state.update { currentState ->
            currentState.copy(
                //coerceAtMost(valorMaximo) garantiza que el valor de la variable no sea mayor a ese valor
                questionsQuantity = (currentState.questionsQuantity + 1).coerceAtMost(maxQuestions)
            )
        }
    }

    // Decrementar la cantidad de preguntas
    fun decrementQuestions() {
        _state.update { currentState ->
            currentState.copy(
                //coerceAtMost() garantiza que el valor de la variable no sea menor a ese valor
                questionsQuantity = (currentState.questionsQuantity - 1).coerceAtLeast(1)
            )
        }
    }

    // Función para actualizar el número de preguntas
    fun updateQuestionsQuantity(quantity: Int) {
        _state.update { currentState ->
            currentState.copy(
                questionsQuantity = quantity,
                currentQuestionIndex = 0
            )  // Resetear índice
        }
    }

    // Función para avanzar a la siguiente pregunta
    fun nextQuestion() {
        _state.update { currentState ->
            //el operador % hace que cuando lleguemos al nº maximo de preguntas, restablezca a 0
            val nextIndex = (currentState.currentQuestionIndex + 1) % currentState.questionsQuantity
            currentState.copy(currentQuestionIndex = nextIndex)
        }
    }

    // Función para obtener la pregunta actual
    fun getCurrentQuestion(): Question {
        val questions = QuestionData.questions
        val currentQuestionIndex = _state.value.currentQuestionIndex
        return questions[currentQuestionIndex]
    }


    /*//Función para manejar el estado del textField
    fun updateNewProductName(newName: String) {
        _state.update { currentState ->
            currentState.copy(newWord = newName)
        }
    }*/


}