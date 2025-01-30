package com.example.trivial.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trivial.TrivialApp
import com.example.trivial.data.TrivialRepository
import com.example.trivial.model.Question
import com.example.trivial.ui.data.QuestionData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VM(private val repository: TrivialRepository) : ViewModel() {
    private val _state =
        MutableStateFlow(State())
    val state: StateFlow<State> =
        _state.asStateFlow()

    //asginamos las preguntas de la API al estado al iniciar la app
    init {
        //lanzamos la corrutina
        viewModelScope.launch {
            try {
                //obtenemos las preguntas de la Api y las transformamos a la estructura de preguntas de nuestro trivial
                val apiQuestions = repository.getApiQuestions().map { it.toQuestion() }
                _state.update { currentState ->
                    currentState.copy(
                        //le asignamos al estado las preguntas obtenidas de la llamada
                        questions = apiQuestions
                    )
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(
                        questions = QuestionData.questions
                    )
                }
            }
        }
    }

    // Incrementar la cantidad de preguntas
    fun incrementQuestions() {
        _state.update { currentState ->
            currentState.copy(
                //coerceAtMost() garantiza que el valor de la variable no sea mayor a ese valor
                questionsQuantity = (currentState.questionsQuantity + 1).coerceAtMost(10)
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
        return _state.value.questions[_state.value.currentQuestionIndex]
    }

    // Función para manejar el clic en una opción
    fun onOptionClicked(selectedOption: String) {
        val currentQuestion = getCurrentQuestion()//obtenemos la pregunta actual
        val isCorrect = currentQuestion.correctAnswer == selectedOption

        // Actualizar el estado
        _state.update { currentState ->
            currentState.copy(
                checkOptions = false, // Desactivamos los botones de las opciones
                check = true,//activamos el botón de ir a la siguiente pregunta
                resultText = if (isCorrect) "¡Respuesta Correcta!" else "Respuesta Incorrecta",
                points = if (isCorrect) _state.value.points + 1 else _state.value.points,
                correctAnswers = if (isCorrect) _state.value.correctAnswers + 1 else _state.value.correctAnswers,
                wrongAnswers = if (!isCorrect) _state.value.wrongAnswers + 1 else _state.value.wrongAnswers,
                selectedOption = selectedOption,
            )
        }
    }


    //función para restablecer los valores de los enabled para la siguiente pregunta
    fun resetEnableds() {
        _state.update { currentstate ->
            currentstate.copy(
                checkOptions = true,
                check = false
            )
        }
    }

    //función para actualizar el record
    fun updateRecord() {
        _state.update { currentstate ->
            currentstate.copy(
                recordPoinment = if (currentstate.points > currentstate.recordPoinment) currentstate.points else currentstate.recordPoinment
            )
        }
    }

    //función para reiniciar el juego
    fun resetGame() {
        _state.update { currentstate ->
            currentstate.copy(
                points = 0,
                correctAnswers = 0,
                wrongAnswers = 0,
                currentQuestionIndex = 0,//se asegura que al mezclar las preguntas, accedemos a la primera, de manera que la próxima vez que se mezclen será distinta
                questions = currentstate.questions.shuffled()//mezclamos las preguntas
            )
        }
    }


    // ViewModel Factory
    /*
    Explicación de la función viewModelFactory:
    - Esta función crea un ViewModelProvider.Factory que inicializa un GameViewModel.
    - La función initializer se encarga de inicializar el GameViewModel con el QuestionRepository.
    - La función viewModelFactory recibe un lambda que retorna un ViewModelProvider.Factory.
    - El lambda recibe un mapa de parámetros y retorna un ViewModel.
    - En este caso, el lambda recibe un mapa con un parámetro APPLICATION_KEY que es un TriviaApplication.
    - El ViewModel se inicializa con el QuestionRepository obtenido del TriviaApplication.
    Este companion object es necesario para poder crear un ViewModelProvider.Factory que inicialice un GameViewModel.
    La palabra reservada companion object permite definir un objeto que es parte de la clase GameViewModel.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TrivialApp)
                val repository = application.container.provideTrivialRepository
                VM(repository)
            }
        }
    }

}



