package com.example.trivial.ui.state


import com.example.trivial.model.Question

data class State(
    val newWord: String = "",
    val resultText: String = "",
    val check: Boolean = false,
    val checkOptions: Boolean = true,
    val questionsQuantity: Int = 1,
    val currentQuestionIndex: Int = 0,  // Índice de la pregunta actual
    val points: Int = 0,
    val correctAnswers: Int = 0,
    val wrongAnswers: Int = 0,
    val recordPoinment: Int = 0,
    val questions: List<Question> = emptyList(),
    val selectedOption: String = "",
)